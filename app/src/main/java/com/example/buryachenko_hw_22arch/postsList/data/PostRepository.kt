package com.example.buryachenko_hw_22arch.postsList.data

import com.example.buryachenko_hw_22arch.postsList.data.api.PostService
import com.example.buryachenko_hw_22arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw_22arch.postsList.data.models.Post
import com.example.buryachenko_hw_22arch.postsList.data.models.PostModel
import com.example.buryachenko_hw_22arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw_22arch.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val database: PostsDatabase,
    private val postService: PostService,
    private val postMapper: PostMapper,
) {

    suspend fun loadBackPosts() {
        database.postsDao().insertPosts(postService.getPosts())
    }

    fun subscribeForInfo(): Flow<Result<List<PostModel>, String>> {
        return database.postsDao().getAllPosts()
            .map { it.sortedWith(compareBy({ !it.isCreatedByUser }, { it.isCreatedByUser })) }
            .map { postMapper.mapping(Result.success(it)) }
    }

    suspend fun updateDb(post: Post) {
        val postsNumber = database.postsDao().getPostsNumber()
        database.postsDao().insertPost(
            Post(
                postId = postsNumber + 1,
                userId = post.userId,
                title = post.title,
                body = post.body,
                isCreatedByUser = true
            )
        )
    }
}
