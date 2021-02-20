package com.example.buryachenko_hw22_arch.present.model

import androidx.annotation.ColorInt

sealed class PostUIModel {
    data class StandardPostUIModel(
        val postId: Int,
        val title: String,
        val userId: String,
        val body: String,
        @ColorInt val backgroundColor: Int
    ) : PostUIModel()

    data class BannedPostUIModel(
        val postId: Int,
        val userId: String
    ) : PostUIModel()
}

