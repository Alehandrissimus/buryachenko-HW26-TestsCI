package com.example.buryachenko_hw_22arch.postsList.data.mappers

import com.example.buryachenko_hw_22arch.postsList.data.models.Post
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.postsList.data.room.PostsDatabase
import javax.inject.Inject

class PostDbMapper @Inject constructor(
    private val database: PostsDatabase
) {
    suspend fun map(post: PostUIModel.StandardPostUIModel): Post {
        return Post(
            postId = database.postsDao().getPostsNumber() + 1,
            userId = post.userId.toInt(),
            title = post.title,
            body = post.body
        )
    }
}
