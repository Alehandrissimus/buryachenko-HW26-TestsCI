package com.example.buryachenko_hw22_arch.data

import com.example.buryachenko_hw22_arch.data.model.Post
import com.example.buryachenko_hw22_arch.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.domain.model.PostModel
import com.example.buryachenko_hw22_arch.tools.Result
import javax.inject.Inject

enum class PostErrors {
    INFO_NOT_LOADED
}

class PostRepository @Inject constructor(
    private val database: PostsDatabase,
    private val postService: PostService,
    private val postMapper: PostMapper
) {
    fun getInfo(): Result<List<PostModel>, String> {
        val posts = mutableListOf<Post>()
        posts.addAll(0, database.postsDao().getAllPosts().asReversed())
        val postsBack = postService.getPosts().execute()

        return if (postsBack.isSuccessful) {
            postsBack.body()?.map { post ->
                posts.add(
                    Post(
                        userId = post.userId,
                        postId = post.postId,
                        title = post.title,
                        body = post.body
                    )
                )
            }
            postMapper.mapping(Result.success(posts))
        } else {
            postMapper.mapping(Result.error(PostErrors.INFO_NOT_LOADED))
        }
    }
}