package com.example.buryachenko_hw22_arch.postsList.data.mappers

import com.example.buryachenko_hw22_arch.postsList.data.UsersStatusedStorage
import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import com.example.buryachenko_hw22_arch.postsList.data.models.PostModel
import com.example.buryachenko_hw22_arch.tools.Result
import org.junit.jupiter.api.Test

internal class PostMapperTest {

    @Test
    fun `Post to PostModel mapper works correctly with standard posts`() {
        val usersStatusedStorage = UsersStatusedStorage.getInstance()
        val postMapper = PostMapper(usersStatusedStorage)

        val posts = listOf(
            Post(
                userId = 11,
                title = "test title",
                body = "test body",
                postId = 1,
                isCreatedByUser = false
            )
        )

        val expectedResult = listOf(
            PostModel.StandardUserPostModel(
                userId = "11",
                title = "test title",
                body = "test body",
                postId = 1,
                hasWarning = false
            )
        )
        val actualResult = postMapper.mapping(Result.success(posts)).successResult
        assert(actualResult == expectedResult)
    }

    @Test
    fun `Post to PostModel mapper works correctly with banned posts`() {
        val usersStatusedStorage = UsersStatusedStorage.getInstance()
        val postMapper = PostMapper(usersStatusedStorage)

        val posts = listOf(
            Post(
                userId = 7,
                title = "test title",
                body = "test body",
                postId = 1,
                isCreatedByUser = false
            )
        )

        val expectedResult = listOf(
            PostModel.BannedUserPostModel(
                userId = "7",
                postId = 1,
            )
        )
        val actualResult = postMapper.mapping(Result.success(posts)).successResult
        assert(actualResult == expectedResult)
    }

    @Test
    fun `Post to PostModel mapper works correctly with warned posts`() {
        val usersStatusedStorage = UsersStatusedStorage.getInstance()
        val postMapper = PostMapper(usersStatusedStorage)

        val posts = listOf(
            Post(
                userId = 4,
                title = "test title",
                body = "test body",
                postId = 1,
                isCreatedByUser = false
            )
        )

        val expectedResult = listOf(
            PostModel.StandardUserPostModel(
                userId = "4",
                title = "test title",
                body = "test body",
                postId = 1,
                hasWarning = true
            )
        )
        val actualResult = postMapper.mapping(Result.success(posts)).successResult
        assert(actualResult == expectedResult)
    }
}
