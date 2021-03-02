package com.example.buryachenko_hw22_arch.postInput.domain

import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import com.example.buryachenko_hw22_arch.postsList.domain.PostVerifier
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class InsertPostUseCase @Inject constructor(
    private val database: PostsDatabase,
    private val postVerifier: PostVerifier
) {
    operator fun invoke(post: PostUIModel.StandardPostUIModel): Observable<InputStates> {

        return Observable.create { emitter ->
            when (val status = postVerifier.verify(post)) {
                InputStates.ACCEPTED -> {

                    Completable.fromAction {
                        database.postsDao().insertPost(
                            Post(
                                postId = database.postsDao().getPostsNumber() + 1,
                                userId = post.userId.toInt(),
                                title = post.title,
                                body = post.body
                            )
                        )
                    }.subscribe()
                    emitter.onNext(status)
                }
                else -> {
                    emitter.onNext(status)
                }
            }
        }

        /*
        return when (val status = postVerifier.verify(post)) {
            /*
            InputStates.ACCEPTED -> {

                database.postsDao().insertPost(Post(
                    postId = database.postsDao().getPostsNumber() + 1,
                    userId = post.userId.toInt(),
                    title = post.title,
                    body = post.body
                ))

                val observable = Observable.create { emitter ->

                    database.postsDao().insertPost(Post(
                        postId = database.postsDao().getPostsNumber() + 1,
                        userId = post.userId.toInt(),
                        title = post.title,
                        body = post.body
                    ))

                }


                /*
                database.postsDao().insertPost(
                    Post(
                        postId = database.postsDao().getPostsNumber() + 1,
                        userId = post.userId.toInt(),
                        title = post.title,
                        body = post.body
                    )
                )

                 */

                InputStates.ACCEPTED
            }
            else -> status

             */
        }

         */
    }
}