package com.example.buryachenko_hw22_arch.postsList.domain

import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.tools.ResourceRepository
import javax.inject.Inject


enum class InputStates {
    ACCEPTED,
    DECLINED,
    CONTAINS_BANNED_WORDS,
    SMALL_TITLE,
    SMALL_BODY,
    BIG_TITLE,
    BIG_BODY
}

class PostVerifier @Inject constructor(
    private val resourceRepository: ResourceRepository
) {
    fun verify(post: PostUIModel.StandardPostUIModel): InputStates {
        var contains = false
        val bannedWordsList =
            resourceRepository.getString(R.string.banned_words).split(", ").map { it.trim() }

        bannedWordsList.forEach {
            if (post.title.contains(it, true)) {
                contains = true
            }
        }

        return when {
            post.title.length < 3 -> {
                InputStates.SMALL_TITLE
            }
            post.body.length < 5 -> {
                InputStates.SMALL_BODY
            }
            post.title.length > 50 -> {
                InputStates.BIG_TITLE
            }
            post.body.length > 500 -> {
                InputStates.BIG_BODY
            }
            contains -> {
                InputStates.CONTAINS_BANNED_WORDS
            }
            else -> {
                InputStates.ACCEPTED
            }
        }
    }
}