package com.example.buryachenko_hw_22arch.postsList.ui

import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.postsList.data.PostRepository
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.postsList.domain.GetPostsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test

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
}