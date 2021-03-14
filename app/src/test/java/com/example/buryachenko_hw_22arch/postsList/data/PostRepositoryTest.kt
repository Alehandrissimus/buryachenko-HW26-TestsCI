package com.example.buryachenko_hw_22arch.postsList.data

import android.content.Context
import androidx.room.Room
import com.example.buryachenko_hw_22arch.postsList.data.api.PostService
import com.example.buryachenko_hw_22arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw_22arch.postsList.data.models.Post
import com.example.buryachenko_hw_22arch.postsList.data.room.PostsDatabase
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class PostRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val context = mockk<Context>(relaxed = true)
    private val testDb = Room.inMemoryDatabaseBuilder(context, PostsDatabase::class.java)
        .setTransactionExecutor(testDispatcher.asExecutor())
        .setQueryExecutor(testDispatcher.asExecutor()).build()

    @Test
    fun `fds`() = testScope.runBlockingTest {
        assert(testDb.postsDao().getPostsNumber() == 0)
        testDb.postsDao().insertPost(
            Post(
                title = "title",
                body = "body",
                userId = 1,
                postId = 1,
                isCreatedByUser = false
            )
        )
        assert(testDb.postsDao().getPostsNumber() == 1)
    }

    @Test
    fun `gtgt`() = testScope.runBlockingTest {
        val postMapper = mockk<PostMapper>(relaxed = true)
        val postService = mockk<PostService>(relaxed = true)
        val a = PostRepository(testDb, postService, postMapper)
        a.loadBackPosts()
    }

    @Test
    fun `ergeg`() {
        val postMapper = mockk<PostMapper>(relaxed = true)
        val postService = mockk<PostService>(relaxed = true)
        val a = PostRepository(testDb, postService, postMapper)
        a.subscribeForInfo()

        verify {
            postMapper.mapping(any())
        }
    }
}