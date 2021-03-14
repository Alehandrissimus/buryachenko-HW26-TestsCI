package com.example.buryachenko_hw_22arch.postInput.ui

import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw_22arch.postsList.data.PostRepository
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.postsList.domain.GetPostsUseCase
import com.example.buryachenko_hw_22arch.postsList.ui.PostsListViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PostInputViewModelTest {

    @Test
    fun `PostsListViewModel calling getPostsUseCase correctly`() {
        val insertPostsUseCase = mockk<InsertPostUseCase>()
        val postRepository = mockk<PostRepository>(relaxed = true)
        /*
        every { InsertPostUseCase.invoke() } returns flow {
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

         */
        PostInputViewModel(insertPostUseCase = insertPostsUseCase)
        verify {

        }
    }

}