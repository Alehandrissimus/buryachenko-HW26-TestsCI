package com.example.buryachenko_hw22_arch.domain

open class PostModel

class StandardPostModel(
    val title: String,
    val userId: String,
    val body: String,
    val hasWarning: Boolean
) : PostModel()

class BannedUserPostModel(
    val userId: String
) : PostModel()