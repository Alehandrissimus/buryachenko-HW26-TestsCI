package com.example.buryachenko_hw22_arch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey val postId: Int,
    val userId: Int,
    val title: String,
    val body: String
)