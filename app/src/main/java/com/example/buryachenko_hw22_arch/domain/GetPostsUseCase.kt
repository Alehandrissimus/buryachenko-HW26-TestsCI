package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.tools.Result
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper,
) {
    operator fun invoke(): Result<List<PostUIModel>, String> {
        return postUIMapper.map(postRepository.getInfo())
    }
}