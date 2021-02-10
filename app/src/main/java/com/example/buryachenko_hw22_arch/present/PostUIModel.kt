package com.example.buryachenko_hw22_arch.present

import androidx.annotation.ColorInt

abstract class PostUIModel

class StandardPostUIModel(
    val title: String,
    val userId: String,
    val body: String,
    @ColorInt val backgroundColor: Int
) : PostUIModel()

class BannedPostUIModel(
    val userId: String
) : PostUIModel()