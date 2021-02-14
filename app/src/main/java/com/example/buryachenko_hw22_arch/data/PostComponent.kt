package com.example.buryachenko_hw22_arch.data

import android.content.Context
import com.example.buryachenko_hw22_arch.datasource.api.PostService
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.domain.UsersStatusedStorage
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.domain.PostUIMapper
import com.example.buryachenko_hw22_arch.present.ResourceRepository
import com.example.buryachenko_hw22_arch.tools.Multithreading
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostComponent {

    fun createPresenter(context: Context): PostPresenter {
        return PostPresenter(
            postRepository = PostRepository(
                multithreading = Multithreading(context),
                postService = createService(),
                postMapper = PostMapper(
                    usersStatusedStorage = UsersStatusedStorage.getInstance()
                )
            ),
            postUIMapper = PostUIMapper(
                resourceRepository = ResourceRepository(context)
            )
        )
    }

    fun createPostRepository(context: Context): PostRepository {
        return PostRepository(
            multithreading = Multithreading(context),
            postService = createService(),
            postMapper = PostMapper(
                usersStatusedStorage = UsersStatusedStorage.getInstance()
            )
        )
    }

    fun createPostUIMapper(context: Context): PostUIMapper {
        return PostUIMapper(
            resourceRepository = ResourceRepository(context)
        )
    }

    private fun createService(): PostService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }
}