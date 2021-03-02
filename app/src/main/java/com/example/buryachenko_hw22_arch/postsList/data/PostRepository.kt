package com.example.buryachenko_hw22_arch.postsList.data

import android.util.Log
import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import com.example.buryachenko_hw22_arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.postsList.data.api.PostService
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw22_arch.postsList.data.models.PostModel
import com.example.buryachenko_hw22_arch.tools.Result
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.inject.Inject

enum class PostErrors {
    INFO_NOT_LOADED,
    DB_NOT_LOADED
}

class PostRepository @Inject constructor(
        private val database: PostsDatabase,
        private val postService: PostService,
        private val postMapper: PostMapper
) {
    fun getInfo(): Observable<Result<List<PostModel>, String>> {
        return Observable.create { emitter ->
            val posts = mutableListOf<Post>()

            database.postsDao().getAllPosts().subscribeOn(Schedulers.io()).subscribe({
                posts.addAll(it.asReversed())
                emitter.onNext(postMapper.mapping(Result.success(posts)))
            }, {
                Log.d("TAG", "database thrown: $it")
            })

            postService.getPosts().subscribeOn(Schedulers.io()).subscribe({
                posts.addAll(it)
                emitter.onNext(postMapper.mapping(Result.success(posts)))
            }, {
                Log.d("TAG", "postService thrown: $it")
            })
        }

    }
}