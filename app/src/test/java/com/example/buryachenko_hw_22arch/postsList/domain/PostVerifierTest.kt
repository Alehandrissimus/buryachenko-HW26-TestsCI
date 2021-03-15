package com.example.buryachenko_hw_22arch.postsList.domain

import android.content.Context
import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.tools.ResourceRepository
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class PostVerifierTest {
    @Test
    fun `post verifier small_title works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postVerifier = PostVerifier(resourceRepository)
        val post = PostUIModel.StandardPostUIModel(
            postId = 1,
            title = "1",
            body = "1",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )

        val actualResult = postVerifier.verify(post)
        val expectedResult = InputStates.SMALL_TITLE

        assert(actualResult == expectedResult)
    }

    @Test
    fun `post verifier small_body works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postVerifier = PostVerifier(resourceRepository)
        val post = PostUIModel.StandardPostUIModel(
            postId = 1,
            title = "gggg1",
            body = "1",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )

        val actualResult = postVerifier.verify(post)
        val expectedResult = InputStates.SMALL_BODY

        assert(actualResult == expectedResult)
    }

    @Test
    fun `post verifier big_title works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postVerifier = PostVerifier(resourceRepository)
        val post = PostUIModel.StandardPostUIModel(
            postId = 1,
            title = "sadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasd",
            body = "111111",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )

        val actualResult = postVerifier.verify(post)
        val expectedResult = InputStates.BIG_TITLE

        assert(actualResult == expectedResult)
    }

    @Test
    fun `post verifier big_body works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postVerifier = PostVerifier(resourceRepository)
        val post = PostUIModel.StandardPostUIModel(
            postId = 1,
            title = "1111",
            body = "sadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasdsadasdsdasdsdasdasd",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )

        val actualResult = postVerifier.verify(post)
        val expectedResult = InputStates.BIG_BODY

        assert(actualResult == expectedResult)
    }

    @Test
    fun `post verifier banned_words works correctly`() {
        val resourceRepository = ResourceRepository(mockk<Context>(relaxed = true))
        val postVerifier = PostVerifier(resourceRepository)
        val post = PostUIModel.StandardPostUIModel(
            postId = 1,
            title = "КуПлЮ ГаРаж",
            body = "fsfdfdsf",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )

        val actualResult = postVerifier.verify(post)
        val expectedResult = InputStates.CONTAINS_BANNED_WORDS

        assert(actualResult == expectedResult)
    }
}