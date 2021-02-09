package com.example.buryachenko_hw22_arch.present

import com.example.buryachenko_hw22_arch.data.Post
import com.example.buryachenko_hw22_arch.data.PostErrors
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.tools.Result

class PostUIMapper {
    fun map(postResult: Result<PostModel, PostErrors>): Result<PostUIModel, String> {
        return postResult.mapSuccess {
            PostUIModel()
        }.mapError { postErrors ->
            "PostError"
        }
    }
}
