package com.example.buryachenko_hw22_arch.domain

import androidx.annotation.ColorInt

open class PostModel

class StandardUserPostModel(
    val title: String,
    val userId: String,
    val body: String,
    @ColorInt val backgroundColor: Int
) : PostModel()

class BannedUserPostModel(
    val userId: String
) : PostModel()