package com.example.buryachenko_hw22_arch.postsList.data.mappers

import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import com.example.buryachenko_hw22_arch.postsList.data.models.PostModel
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.data.room.PostsDatabase
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

    fun map(post: PostModel.StandardUserPostModel): Post {
        return Post(
            postId = post.postId,
            userId = post.userId.toInt(),
            title = post.title,
            body = post.body
        )
    }

    fun map(postList: List<PostModel>): List<Post> {
        return postList.map { postModel ->
            when(postModel) {
                is PostModel.StandardUserPostModel -> {
                    Post(
                        postId = postModel.postId,
                        userId = postModel.userId.toInt(),
                        title = postModel.title,
                        body = postModel.body
                    )
                }
                is PostModel.BannedUserPostModel -> {
                    Post(
                        postId = postModel.postId,
                        userId = postModel.userId.toInt(),
                        body = "",
                        title = ""
                    )
                }
            }
        }
    }
}