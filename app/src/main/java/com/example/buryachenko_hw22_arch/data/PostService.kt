package com.example.buryachenko_hw22_arch.data

import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("posts/")
    fun getInfo(): Call<List<Post>>
}