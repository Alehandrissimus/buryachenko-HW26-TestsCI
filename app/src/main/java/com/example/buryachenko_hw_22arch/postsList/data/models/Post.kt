package com.example.buryachenko_hw_22arch.postsList.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Post(
        @SerializedName("id")
        @PrimaryKey
        val postId: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("body")
        val body: String,

        val isCreatedByUser: Boolean = false,
)
