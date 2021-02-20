package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.data.PostErrors
import com.example.buryachenko_hw22_arch.data.UsersStatusedStorage
import com.example.buryachenko_hw22_arch.data.model.Post
import com.example.buryachenko_hw22_arch.domain.model.PostModel
import com.example.buryachenko_hw22_arch.domain.model.UserStatus
import com.example.buryachenko_hw22_arch.tools.Result
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val usersStatusedStorage: UsersStatusedStorage
) {
    fun mapping(postResult: Result<List<Post>, PostErrors>): Result<List<PostModel>, String> {
        return postResult.mapSuccess { postsList ->
            postsList.map { post ->
                val userStatused = usersStatusedStorage.getList().find { it.userId == post.userId }
                if (userStatused != null) {
                    return@map when (userStatused.status) {

                        UserStatus.WARNING -> {
                            PostModel.StandardUserPostModel(
                                postId = post.postId,
                                userId = post.userId.toString(),
                                title = post.title,
                                body = post.body,
                                hasWarning = true
                            )
                        }

                        UserStatus.BANNED -> {
                            PostModel.BannedUserPostModel(
                                postId = post.postId,
                                userId = post.userId.toString()
                            )
                        }
                        else -> throw IllegalArgumentException()
                    }

                } else {

                    return@map PostModel.StandardUserPostModel(
                        postId = post.postId,
                        userId = post.userId.toString(),
                        title = post.title,
                        body = post.body,
                        hasWarning = false
                    )
                }
            }
        }.mapError { errors ->
            "$errors"
        }
    }
}