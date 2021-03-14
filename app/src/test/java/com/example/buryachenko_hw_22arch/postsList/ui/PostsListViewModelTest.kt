package com.example.buryachenko_hw_22arch.postsList.ui

import arrow.core.extensions.id.applicative.just
import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.CoroutineTestRule
import com.example.buryachenko_hw_22arch.postsList.data.PostRepository
import com.example.buryachenko_hw_22arch.postsList.data.api.PostService
import com.example.buryachenko_hw_22arch.postsList.data.models.Post
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.postsList.domain.GetPostsUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

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

    @Test
    fun `tegege`() {
        val getPostsUseCase = mockk<GetPostsUseCase>()
        val postRepository = mockk<PostRepository>(relaxed = true)
        val viewModel = PostsListViewModel(getPostsUseCase, postRepository)
        val fragment = PostsFragment.newInstance()
        fragment.postsViewModel = viewModel

    }

    /*

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val context: CoroutineContext = testDispatcher

    @Test
    fun `testsets`() = testScope.runBlockingTest {
        val retro = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .callbackExecutor(testDispatcher.asExecutor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retro.create(PostService::class.java)
        val service2 = mockk<PostService>(relaxed = true)
        val a = service.getPosts()
        assert(a.first() is Post)

        verify {
            testScope.launch {
                service.getPosts()
            }
        }
    }

     */

}