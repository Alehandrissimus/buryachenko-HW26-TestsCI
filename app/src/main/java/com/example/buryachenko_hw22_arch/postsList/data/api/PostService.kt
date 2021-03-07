package com.example.buryachenko_hw22_arch.postsList.data.api

import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import retrofit2.http.GET

interface PostService {
    @GET("posts/")
    suspend fun getPosts(): List<Post>
}