package com.example.buryachenko_hw22_arch.present

import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.domain.model.PostModel
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.tools.Result
import javax.inject.Inject

class PostUIMapper @Inject constructor(
    private val resourceRepository: ResourceRepository
) {
    fun map(postResult: Result<List<PostModel>, String>): Result<List<PostUIModel>, String> {
        return postResult.mapSuccess { postModelList ->
            postModelList.map { postModel ->
                when (postModel) {
                    is PostModel.StandardUserPostModel -> {
                        PostUIModel.StandardPostUIModel(
                            postId = postModel.postId,
                            title = postModel.title,
                            body = postModel.body,
                            userId = if (postModel.hasWarning) {
                                resourceRepository.getString(
                                    R.string.warning_name_template,
                                    postModel.userId
                                )
                            } else {
                                postModel.userId
                            },
                            backgroundColor = if (postModel.hasWarning) {
                                resourceRepository.getColor(R.color.yellow)
                            } else {
                                resourceRepository.getColor(R.color.design_default_color_background)
                            }
                        )
                    }
                    is PostModel.BannedUserPostModel -> {
                        PostUIModel.BannedPostUIModel(
                            postId = postModel.postId,
                            userId = resourceRepository.getString(
                                R.string.banned_name_template,
                                postModel.userId
                            ),
                        )
                    }
                }
            }
        }.mapError { error ->
            resourceRepository.getString(R.string.error_template, error)
        }
    }
}
