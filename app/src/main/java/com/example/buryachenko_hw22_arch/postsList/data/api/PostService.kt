package com.example.buryachenko_hw22_arch.postsList.data.api

import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("posts/")
    fun getPosts(): Observable<List<Post>>
}