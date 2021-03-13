package com.example.buryachenko_hw_22arch.postsList.data.mappers

import android.content.Context
import androidx.room.Room
import com.example.buryachenko_hw_22arch.postsList.data.models.Post
import com.example.buryachenko_hw_22arch.postsList.data.room.PostsDatabase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PostUIMapperTest {

    private lateinit var db: PostsDatabase

    @BeforeAll
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            mockk<Context>(),
            PostsDatabase::class.java
        ).build()

    }

    @AfterAll
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `database inserting post works correctly`() {
        val scope = TestCoroutineScope()
        val post = Post(
            postId = 1,
            title = "title",
            body = "bodyyy",
            userId = 1,
            isCreatedByUser = false,
        )
        scope.launch {
            db.postsDao().insertPost(post)

            val result = db.postsDao().getAll()
            assert(result[0] == post)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `rgrgg`() = runBlocking {
        val post = Post(
            postId = 1,
            title = "title",
            body = "bodyyy",
            userId = 1,
            isCreatedByUser = false,
        )

        db.postsDao().insertPost(post)

        val result = db.postsDao().getAll()
        assert(result[0] == post)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `ggrggrgrg`() = runBlocking {
        val a = db.postsDao().getPostsNumber()
        assertEquals(2, a)
    }
}
