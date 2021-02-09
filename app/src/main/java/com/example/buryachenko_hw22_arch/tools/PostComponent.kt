package com.example.buryachenko_hw22_arch.tools

import android.content.Context
import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostComponent {

    fun createPresenter(context: Context): PostPresenter {
        return PostPresenter(
            postRepository = PostRepository(
                multithreading = Multithreading(context),
                infoService = createService(),
                postMapper = PostMapper()
            ),
            postUIMapper = PostUIMapper(
                //resourceRepository = ResourceRepository(context)
            )
        )
    }

    private fun createService(): InfoService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InfoService::class.java)
    }
}