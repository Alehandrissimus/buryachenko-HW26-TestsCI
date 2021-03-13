package com.example.buryachenko_hw22_arch.postsList.ui

import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.github.difflib.DiffUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PostRVDiffUtilsTest {

    @Test
    fun `PostRVDiffUtils areItemsTheSame works correctly`() {
        val diffUtils = PostRVDiffUtils()

        val standardPost1 = PostUIModel.StandardPostUIModel(
            postId = 11,
            title = "standardPost1",
            body = "standardPost1",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )
        val standardPost2 = PostUIModel.StandardPostUIModel(
            postId = 10,
            title = "standardPost1",
            body = "standardPost1",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )

        val bannedPost1 = PostUIModel.BannedPostUIModel(
            postId = 11,
            userId = "11",
        )
        assertAll("Group",
            { assert(diffUtils.areItemsTheSame(standardPost1, standardPost1)) },
            { assert(diffUtils.areItemsTheSame(bannedPost1, bannedPost1)) },
            { assert(!diffUtils.areItemsTheSame(standardPost1, bannedPost1)) },
            { assert(!diffUtils.areItemsTheSame(standardPost1, standardPost2)) },
        )
    }

    @Test
    fun `PostRVDiffUtils areContentsTheSame works correctly`() {
        val diffUtils = PostRVDiffUtils()

        val standardPost1 = PostUIModel.StandardPostUIModel(
            postId = 11,
            title = "standardPost1",
            body = "standardPost1",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )
        val standardPost2 = PostUIModel.StandardPostUIModel(
            postId = 11,
            title = "standardPost2",
            body = "standardPost2",
            userId = "11",
            backgroundColor = R.color.design_default_color_background
        )
        val bannedPost1 = PostUIModel.BannedPostUIModel(
            postId = 10,
            userId = "11",
        )
        val bannedPost2 = PostUIModel.BannedPostUIModel(
            postId = 11,
            userId = "12",
        )

        assertAll("Group",
            { assert(diffUtils.areContentsTheSame(standardPost1, standardPost1)) },
            { assert(!diffUtils.areContentsTheSame(standardPost1, standardPost2)) },
            { assert(!diffUtils.areContentsTheSame(bannedPost1, bannedPost2)) },
        )
    }

}