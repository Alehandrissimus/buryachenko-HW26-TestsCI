package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.data.Post
import com.example.buryachenko_hw22_arch.data.PostErrors
import com.example.buryachenko_hw22_arch.tools.Result

class PostMapper(private val usersStatusedStorage: UsersStatusedStorage) {
    fun mapping(postResult: Result<List<Post>, PostErrors>): Result<List<PostModel>, String> {
        return postResult.mapSuccess { postList ->
            postList.map { post ->
                val userStatused = usersStatusedStorage.getList().find { it.userId == post.userId }
                if (userStatused != null) {
                    return@map when (userStatused.status) {
                        UserStatus.WARNING -> {
                            StandardUserPostModel(
                                userId = post.userId.toString(),
                                title = post.title,
                                body = post.body,
                                hasWarning = true
                            )
                        }
                        UserStatus.BANNED -> {
                            BannedUserPostModel(
                                userId = post.userId.toString()
                            )
                        }
                        else -> throw IllegalArgumentException()
                    }
                } else {
                    return@map StandardUserPostModel(
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