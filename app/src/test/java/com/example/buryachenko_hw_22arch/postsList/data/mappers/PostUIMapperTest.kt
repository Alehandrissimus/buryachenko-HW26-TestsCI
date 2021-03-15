package com.example.buryachenko_hw_22arch.postsList.data.mappers

import android.content.Context
import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.postsList.data.models.PostModel
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.tools.ResourceRepository
import com.example.buryachenko_hw_22arch.tools.Result
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class PostUIMapperTest {

    @Test
    fun `PostUIMapper Standard model mapping works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postUIMapper = PostUIMapper(resourceRepository)
        val post = Result.success<List<PostModel>, String>(
            listOf(
                PostModel.StandardUserPostModel(
                    title = "title",
                    body = "bodyt",
                    postId = 1,
                    userId = "1",
                    hasWarning = false
                )
            )
        )
        val expectedResult = Result.success<List<PostUIModel>, String>(
            listOf(
                PostUIModel.StandardPostUIModel(
                    title = "title",
                    body = "bodyt",
                    postId = 1,
                    userId = "1",
                    backgroundColor = resourceRepository.getColor(R.color.design_default_color_background)
                )
            )
        )

        val actualResult = postUIMapper.map(post)
        assert(actualResult.successResult == expectedResult.successResult)
    }

    @Test
    fun `PostUIMapper Standard model mapping works correctly2`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postUIMapper = PostUIMapper(resourceRepository)
        val post = Result.success<List<PostModel>, String>(
            listOf(
                PostModel.StandardUserPostModel(
                    title = "title",
                    body = "bodyt",
                    postId = 1,
                    userId = "1",
                    hasWarning = false
                )
            )
        )
        val expectedResult = Result.success<List<PostUIModel>, String>(
            listOf(
                PostUIModel.StandardPostUIModel(
                    title = "titl1",
                    body = "bodyt",
                    postId = 1,
                    userId = "2",
                    backgroundColor = resourceRepository.getColor(R.color.yellow)
                )
            )
        )

        val actualResult = postUIMapper.map(post)
        assert(actualResult.successResult != expectedResult.successResult)
    }

    @Test
    fun `PostUIMapper Banned model mapping works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postUIMapper = PostUIMapper(resourceRepository)
        val post = Result.success<List<PostModel>, String>(
            listOf(
                PostModel.BannedUserPostModel(
                    userId = "1",
                    postId = 4,
                )
            )
        )
        val expectedResult = Result.success<List<PostUIModel>, String>(
            listOf(
                PostUIModel.BannedPostUIModel(
                    userId = "",
                    postId = 4,
                )
            )
        )

        val actualResult = postUIMapper.map(post)
        assert(actualResult.successResult == expectedResult.successResult)
    }

    @Test
    fun `PostUIMapper Banned model mapping works correctly2`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postUIMapper = PostUIMapper(resourceRepository)
        val post = Result.success<List<PostModel>, String>(
            listOf(
                PostModel.BannedUserPostModel(
                    userId = "3",
                    postId = 4,
                )
            )
        )
        val expectedResult = Result.success<List<PostUIModel>, String>(
            listOf(
                PostUIModel.BannedPostUIModel(
                    userId = "1",
                    postId = 2,
                )
            )
        )

        val actualResult = postUIMapper.map(post)
        assert(actualResult.successResult != expectedResult.successResult)
    }

    @Test
    fun `PostUIMapper error mapping works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postUIMapper = PostUIMapper(resourceRepository)
        val post = Result.error<List<PostModel>, String>("Some error")

        val expectedResult = Result.error<List<PostUIModel>, String>("")
        val actualResult = postUIMapper.map(post)
        assert(actualResult.errorResult == expectedResult.errorResult)
    }
}
