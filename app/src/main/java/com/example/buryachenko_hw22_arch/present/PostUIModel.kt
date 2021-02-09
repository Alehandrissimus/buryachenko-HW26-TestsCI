package com.example.buryachenko_hw22_arch.present

import com.example.buryachenko_hw22_arch.domain.PostModel

open class PostUIModel

class StandardPostUIModel(
    val title: String,
    val userId: String,
    val body: String,
    val hasWarning: Boolean
) : PostUIModel()

class BannedUserPosUItModel(
    val userId: String
) : PostUIModel()
