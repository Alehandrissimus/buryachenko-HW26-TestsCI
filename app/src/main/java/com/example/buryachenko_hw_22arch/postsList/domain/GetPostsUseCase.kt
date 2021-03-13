package com.example.buryachenko_hw_22arch.postsList.domain

import com.example.buryachenko_hw_22arch.postsList.data.PostRepository
import com.example.buryachenko_hw_22arch.postsList.data.mappers.PostUIMapper
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
        private val postRepository: PostRepository,
        private val postUIMapper: PostUIMapper,
) {
    operator fun invoke(): Flow<Result<List<PostUIModel>, String>> {
        return postRepository.subscribeForInfo()
                .map { postUIMapper.map(it) }
    }
}
