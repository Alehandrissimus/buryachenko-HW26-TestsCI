package com.example.buryachenko_hw22_arch.data

import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.present.PostUIModel
import com.example.buryachenko_hw22_arch.tools.AsyncOperation
import com.example.buryachenko_hw22_arch.tools.InfoService
import com.example.buryachenko_hw22_arch.tools.Multithreading
import com.example.buryachenko_hw22_arch.tools.Result

enum class PostErrors {
    INFO_NOT_LOADED
}

class PostRepository(
    private val multithreading: Multithreading,
    private val infoService: InfoService,
    private val postMapper: PostMapper
) {
    fun getInfo(): AsyncOperation<Result<PostModel, PostErrors>> {
        val asyncOperation = multithreading.async<Result<Post, PostErrors>> {
            val info = infoService.getInfo().execute().body()
                ?: return@async Result.error(PostErrors.INFO_NOT_LOADED)

            return@async Result.success(
                Post(
                    userId = 1,
                    title = "asdasd",
                    body =  " gitjgoirtgrgt"
                )
            )
        }

        return asyncOperation.map(postMapper::map)
    }
}