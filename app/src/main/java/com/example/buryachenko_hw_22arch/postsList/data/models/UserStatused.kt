package com.example.buryachenko_hw_22arch.postsList.data.models

enum class UserStatus {
    STANDARD,
    WARNING,
    BANNED
}

data class UserStatused(
    val userId: Int,
    val status: UserStatus
)
