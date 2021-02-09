package com.example.buryachenko_hw22_arch.tools

import com.example.buryachenko_hw22_arch.data.Post
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface InfoService {
    @GET("posts/")
    fun getInfo(): Call<Post>
}