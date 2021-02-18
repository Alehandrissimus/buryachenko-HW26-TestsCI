package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.data.model.Post
import com.example.buryachenko_hw22_arch.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.present.model.StandardPostUIModel
import javax.inject.Inject

class InsertPostUseCase @Inject constructor(
    private val database: PostsDatabase,
    private val postVerifier: PostVerifier
) {
    operator fun invoke(post: StandardPostUIModel): InputStates {

        return when (val status = postVerifier.verify(post)) {
            InputStates.ACCEPTED -> {
                database.postsDao().insertPost(
                    Post(
                        postId = database.postsDao().getAllPosts().size + 1,
                        userId = post.userId.toInt(),
                        title = post.title,
                        body = post.body
                    )
                )
                InputStates.ACCEPTED
            }
            else -> status
        }
    }
}