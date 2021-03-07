package com.example.buryachenko_hw22_arch.postsList.data

import android.util.Log
import com.example.buryachenko_hw22_arch.postsList.data.api.PostService
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import com.example.buryachenko_hw22_arch.postsList.data.models.PostModel
import com.example.buryachenko_hw22_arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val database: PostsDatabase,
    private val postService: PostService,
    private val postMapper: PostMapper,
) {

    private suspend fun loadBackPosts() {
        try {
            database.postsDao().insertPosts(postService.getPosts())
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
        }
    }

    suspend fun subscribeForInfo(): Flow<Result<List<PostModel>, String>> {
        loadBackPosts()
        return database.postsDao().getAllPosts()
            .map { it.sortedBy { it.isCreatedByUser }.asReversed() }
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