package com.example.buryachenko_hw22_arch.domain

import com.example.buryachenko_hw22_arch.present.model.StandardPostUIModel

enum class InputStates {
    ACCEPTED,
    DECLINED,
    CONTAINS_BANNED_WORDS,
    SMALL_TITLE,
    SMALL_BODY,
    BIG_TITLE,
    BIG_BODY
}

class PostVerifier {
    fun verify(post: StandardPostUIModel): InputStates {
        var contains = false
        val bannedWordsList = listOf("реклама", "товар", "куплю")

        bannedWordsList.forEach {
            if (post.title.contains(it, true)) {
                contains = true
            }
        }

        return when {
            contains -> {
                InputStates.CONTAINS_BANNED_WORDS
            }
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
            else -> {
                InputStates.ACCEPTED
            }
        }
    }
}