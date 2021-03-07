package com.example.buryachenko_hw22_arch

import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import com.example.buryachenko_hw22_arch.postsList.domain.PostVerifier
import com.example.buryachenko_hw22_arch.tools.ResourceRepository
import org.junit.Test
import javax.inject.Inject

internal class PostVerifierTest @Inject constructor(
    private val resourceRepository: ResourceRepository
) {

    @Test
    fun `post verifier small_title works correctly`() {
        val postVerifier = PostVerifier(resourceRepository)
        val postUiModel = PostUIModel.StandardPostUIModel(
            userId = "1",
            title = "",
            body = "",
            postId = 5,
            backgroundColor = resourceRepository.getColor(R.color.design_default_color_primary)
        )

        assert(postVerifier.verify(postUiModel) == InputStates.SMALL_TITLE)
    }

    @Test
    fun `post verifier small_body works correctly`() {
        val postVerifier = PostVerifier(resourceRepository)
        val postUiModel = PostUIModel.StandardPostUIModel(
            userId = "1",
            title = "grgrrgrgrg",
            body = "",
            postId = 5,
            backgroundColor = resourceRepository.getColor(R.color.design_default_color_primary)
        )

        assert(postVerifier.verify(postUiModel) == InputStates.SMALL_BODY)
    }

    @Test
    fun `post verifier big_title works correctly`() {
        val postVerifier = PostVerifier(resourceRepository)
        val postUiModel = PostUIModel.StandardPostUIModel(
            userId = "1",
            title = "grrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
            body = "",
            postId = 5,
            backgroundColor = resourceRepository.getColor(R.color.design_default_color_primary)
        )

        assert(postVerifier.verify(postUiModel) == InputStates.BIG_TITLE)
    }

    @Test
    fun `post verifier big_body works correctly`() {
        val postVerifier = PostVerifier(resourceRepository)
        val postUiModel = PostUIModel.StandardPostUIModel(
            userId = "1",
            title = "",
            body = "ggrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
            postId = 5,
            backgroundColor = resourceRepository.getColor(R.color.design_default_color_primary)
        )
        assert(postVerifier.verify(postUiModel) == InputStates.BIG_BODY)
    }
}