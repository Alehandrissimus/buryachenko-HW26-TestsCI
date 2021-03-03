package com.example.buryachenko_hw22_arch.postsList.data

import android.annotation.SuppressLint
import android.util.Log
import com.example.buryachenko_hw22_arch.postsList.data.api.PostService
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostDbMapper
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import com.example.buryachenko_hw22_arch.postsList.data.models.PostModel
import com.example.buryachenko_hw22_arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.tools.Result
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val database: PostsDatabase,
    private val postService: PostService,
    private val postMapper: PostMapper,
    private val postDbMapper: PostDbMapper,
) {

    private lateinit var subject: ReplaySubject<Result<List<PostModel>, String>>

    private fun getInfo(): Observable<Result<List<PostModel>, String>> {
        return Observable.create { emitter ->
            val posts = mutableListOf<Post>()

            database.postsDao().getAllPosts().subscribeOn(Schedulers.io()).subscribe({
                posts.addAll(it.asReversed())
                postService.getPosts().subscribe { servicePosts ->
                    posts.addAll(servicePosts)
                    emitter.onNext(postMapper.mapping(Result.success(posts)))
                    emitter.onComplete()
                }
            }, {
                Log.d("TAG", "database thrown: $it")
            })
        }
    }

    @SuppressLint("CheckResult")
    fun subscribeForInfo(): ReplaySubject<Result<List<PostModel>, String>> {
        subject = ReplaySubject.create()

        getInfo().subscribe {
            subject.onNext(Result.success(it.successResult.toMutableList()))
        }

        return subject
    }

    fun updateDb(post: Post) {
        Completable.fromAction {
            val poste = Post(
                postId = database.postsDao().getPostsNumber() + 1,
                userId = post.userId,
                title = post.title,
                body = post.body
            )
            database.postsDao().insertPost(poste)
        }.subscribe()

        val postList = mutableListOf(post)
        subject.value?.let {
            postList.addAll(postDbMapper.map(it.successResult))
        }
        subject.onNext(postMapper.mapping(Result.success(postList)))
    }


}