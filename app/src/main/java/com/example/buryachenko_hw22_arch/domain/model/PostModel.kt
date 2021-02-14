package com.example.buryachenko_hw22_arch.domain

abstract class PostModel

class StandardUserPostModel(
    val title: String,
    val userId: String,
    val body: String,
    val hasWarning: Boolean
) : PostModel()

class BannedUserPostModel(
    val userId: String
) : PostModel()