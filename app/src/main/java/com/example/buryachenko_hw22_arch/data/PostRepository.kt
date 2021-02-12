package com.example.buryachenko_hw22_arch.data

import com.example.buryachenko_hw22_arch.datasource.api.PostService
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.tools.AsyncOperation
import com.example.buryachenko_hw22_arch.tools.Multithreading
import com.example.buryachenko_hw22_arch.tools.Result

enum class PostErrors {
    INFO_NOT_LOADED
}

class PostRepository(
    private val multithreading: Multithreading,
    private val postService: PostService,
    private val postMapper: PostMapper
) {
    fun getInfo(): AsyncOperation<Result<List<PostModel>, String>> {
        val asyncOperation = multithreading.async<Result<List<Post>, PostErrors>> {

            val info = postService.getInfo().execute().body()
                ?: return@async Result.error(PostErrors.INFO_NOT_LOADED)

            val result = mutableListOf<Post>()

            info.map { post ->
                result.add(
                    Post(
                        userId = post.userId,
                        title = post.title,
                        body = post.body
                    )
                )
            }

            return@async Result.success(result)
        }

        return asyncOperation.map(postMapper::mapping)
    }
}