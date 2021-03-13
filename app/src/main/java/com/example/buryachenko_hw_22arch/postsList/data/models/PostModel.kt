package com.example.buryachenko_hw_22arch.postsList.data.models

sealed class PostModel {
    data class StandardUserPostModel(
        val postId: Int,
        val title: String,
        val userId: String,
        val body: String,
        val hasWarning: Boolean
    ) : PostModel()

    data class BannedUserPostModel(
        val postId: Int,
        val userId: String
    ) : PostModel()
}
