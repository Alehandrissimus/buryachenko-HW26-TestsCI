package com.example.buryachenko_hw22_arch.present

import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.domain.BannedUserPostModel
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.domain.StandardUserPostModel
import com.example.buryachenko_hw22_arch.tools.Result

class PostUIMapper(private val resourceRepository: ResourceRepository) {
    fun map(postResult: Result<List<PostModel>, String>): Result<List<PostUIModel>, String> {
        return postResult.mapSuccess { postModelList ->
            postModelList.map { postModel ->
                when (postModel) {
                    is StandardUserPostModel -> {
                        if (postModel.hasWarning) {
                            StandardPostUIModel(
                                userId = resourceRepository.getString(
                                    R.string.warning_name_template,
                                    postModel.userId
                                ),
                                title = postModel.title,
                                body = postModel.body,
                                backgroundColor = resourceRepository.getColor(R.color.yellow)
                            )
                        } else {
                            StandardPostUIModel(
                                userId = postModel.userId,
                                title = postModel.title,
                                body = postModel.body,
                                backgroundColor = resourceRepository.getColor(R.color.design_default_color_background)
                            )
                        }
                    }
                    is BannedUserPostModel -> {
                        BannedPostUIModel(
                            userId = resourceRepository.getString(
                                R.string.banned_name_template,
                                postModel.userId
                            ),
                        )
                    }
                    else -> throw IllegalArgumentException()
                }
            }
        }.mapError { error ->
            resourceRepository.getString(R.string.error_template, error)
        }
    }
}
