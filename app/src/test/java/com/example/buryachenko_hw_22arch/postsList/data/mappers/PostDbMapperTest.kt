package com.example.buryachenko_hw_22arch.postsList.data.mappers

import android.content.Context
import androidx.room.Room
import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.postsList.data.models.Post
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.postsList.data.room.PostsDatabase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.Test

internal class PostDbMapperTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `PostUI to Post mapper works correctly`() {
        val scope = TestCoroutineScope()
        val mockContext = mockk<Context>(relaxed = true)
        val postsDatabase = Room.databaseBuilder(
            mockContext,
            PostsDatabase::class.java, "database"
        ).build()
        val postDbMapper = PostDbMapper(postsDatabase)

        val postUI = PostUIModel.StandardPostUIModel(
            userId = "11",
            title = "test title",
            body = "test body",
            postId = 1,
            backgroundColor = R.color.design_default_color_background
        )

        scope.launch {
            val expectedResult: Post = Post(
                postId = postsDatabase.postsDao().getPostsNumber() + 1,
                userId = postUI.userId.toInt(),
                title = postUI.title,
                body = postUI.body
            )
            val actualResult: Post = postDbMapper.map(postUI)
            assert(actualResult == expectedResult)
        }
    }

}