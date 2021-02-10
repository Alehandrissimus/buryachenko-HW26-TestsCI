package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.data.Post
import com.example.buryachenko_hw22_arch.data.PostErrors
import com.example.buryachenko_hw22_arch.present.ResourceRepository
import com.example.buryachenko_hw22_arch.tools.Result
import java.lang.IllegalArgumentException

class PostMapper(
    private val resourceRepository: ResourceRepository,
    private val usersStatusedStorage: UsersStatusedStorage
    ) {
    fun map(postResult: Result<List<Post>, PostErrors>) : Result<List<PostModel>, String> {
        return postResult.mapSuccess { postList ->
            postList.map { post ->
                val userStatused = usersStatusedStorage.getList().find { it.userId == post.userId }
                if (userStatused != null) {
                    return@map when (userStatused.status) {
                        UserStatus.WARNING -> {
                            StandardUserPostModel(
                                userId = resourceRepository.getString(
                                    R.string.warning_name_template,
                                    post.userId.toString()
                                ),
                                title = post.title,
                                body = post.body,
                                backgroundColor = resourceRepository.getColor(R.color.yellow)
                            )
                        }
                        UserStatus.BANNED -> {
                            BannedUserPostModel(
                                userId = resourceRepository.getString(
                                    R.string.banned_name_template,
                                    post.userId.toString()
                                )
                            )
                        }
                        else -> throw IllegalArgumentException()
                    }
                } else {
                    return@map StandardUserPostModel(
                        userId = post.userId.toString(),
                        title = post.title,
                        body = post.body,
                        backgroundColor = resourceRepository.getColor(R.color.design_default_color_background)
                    )
                }
            }


            /*
            postList.map { post ->
                when (post.userId) {
                    3 -> {
                        StandardUserPostModel(
                            userId = resourceRepository.getString(R.string.warning_name_template, post.userId.toString()),
                            title = post.title,
                            body = post.body,
                            backgroundColor = resourceRepository.getColor(R.color.yellow)
                        )
                    }
                    4 -> {
                        StandardUserPostModel(
                            userId = resourceRepository.getString(R.string.warning_name_template, post.userId.toString()),
                            title = post.title,
                            body = post.body,
                            backgroundColor = resourceRepository.getColor(R.color.yellow)
                        )
                    }
                    7 -> {
                        BannedUserPostModel(
                            userId = resourceRepository.getString(R.string.banned_name_template, post.userId.toString())
                        )
                    }
                    else -> {
                        StandardUserPostModel(
                            userId = post.userId.toString(),
                            title = post.title,
                            body = post.body,
                            backgroundColor = resourceRepository.getColor(R.color.design_default_color_background)
                        )
                    }
                }
            }

             */
        }.mapError { errors ->
            "$errors"
        }


    }
}