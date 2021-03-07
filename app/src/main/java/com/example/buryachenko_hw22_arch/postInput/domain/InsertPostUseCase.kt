package com.example.buryachenko_hw22_arch.postInput.domain

import com.example.buryachenko_hw22_arch.postsList.data.PostRepository
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostDbMapper
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import com.example.buryachenko_hw22_arch.postsList.domain.PostVerifier
import javax.inject.Inject

class InsertPostUseCase @Inject constructor(
    private val postVerifier: PostVerifier,
    private val postRepository: PostRepository,
    private val postDbMapper: PostDbMapper
) {
    suspend operator fun invoke(post: PostUIModel.StandardPostUIModel): InputStates {
        return when (val status = postVerifier.verify(post)) {
            InputStates.ACCEPTED -> {
                postRepository.updateDb(postDbMapper.map(post))
                status
            }
            else -> {
                status
            }
        }
    }
}