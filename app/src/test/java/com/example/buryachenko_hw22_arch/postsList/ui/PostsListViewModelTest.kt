package com.example.buryachenko_hw22_arch.postsList.ui

import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.CoroutineTestRule
import com.example.buryachenko_hw22_arch.postsList.data.PostRepository
import com.example.buryachenko_hw22_arch.postsList.data.api.PostService
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.GetPostsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
internal class PostsListViewModelTest {

    @Test
    fun `PostsListViewModel calling getPostsUseCase correctly`() {
        val getPostsUseCase = mockk<GetPostsUseCase>()
        val postRepository = mockk<PostRepository>(relaxed = true)
        every { getPostsUseCase.invoke() } returns flow {
            Result.success(
                listOf(
                    PostUIModel.StandardPostUIModel(
                        userId = "11",
                        body = "post body",
                        title = "post title",
                        postId = 11,
                        backgroundColor = R.color.design_default_color_background
                    )
                )
            )
        }
        PostsListViewModel(getPostsUseCase, postRepository).subscribeOnDatabase()
        verify {
            getPostsUseCase.invoke()
        }
    }

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Test
    fun `testsets`() = coroutineTestRule.testDispatcher.runBlockingTest {
        val retro = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retro.create(PostService::class.java)
        val a = service.getPosts()
    }

}