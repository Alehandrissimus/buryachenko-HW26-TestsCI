package com.example.buryachenko_hw22_arch.data

import com.example.buryachenko_hw22_arch.data.model.Post
import com.example.buryachenko_hw22_arch.datasource.api.PostService
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.tools.Result
import javax.inject.Inject

enum class PostErrors {
    INFO_NOT_LOADED
}

class PostRepository @Inject constructor(
    private val postService: PostService,
    private val postMapper: PostMapper
) {
    fun getInfo(): Result<List<PostModel>, String> {
        val list = mutableListOf<Post>() // add local storage
        val info = postService.getInfo().execute()

        return if (info.isSuccessful) {
            info.body()?.map { post ->
                list.add(
                    Post(
                        userId = post.userId,
                        title = post.title,
                        body = post.body
                    )
                )
            }
            postMapper.mapping(Result.success(list))
        } else {
            postMapper.mapping(Result.error(PostErrors.INFO_NOT_LOADED))
        }
    }
}