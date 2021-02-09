package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.data.Post
import com.example.buryachenko_hw22_arch.data.PostErrors
import com.example.buryachenko_hw22_arch.tools.Result

class PostMapper {
    fun map(postResult: Result<Post, PostErrors>) : Result<PostModel, PostErrors> {
        return postResult.mapSuccess { info ->
            return@mapSuccess when (info.userId) {
                3 or 4 ->  {
                    StandardPostModel(
                        userId = info.userId.toString(),
                        hasWarning = true,
                        title = info.title,
                        body = info.body
                    )
                }
                7 -> {
                    BannedUserPostModel(
                        userId = info.userId.toString()
                    )
                }
                else -> {
                    StandardPostModel(
                        userId = info.userId.toString(),
                        hasWarning = false,
                        title = info.title,
                        body = info.body
                    )
                }
            }
        }.mapError { error ->
            error
        }
    }
}