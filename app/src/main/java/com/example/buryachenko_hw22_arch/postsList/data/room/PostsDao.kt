package com.example.buryachenko_hw22_arch.postsList.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import io.reactivex.Observable

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Observable<List<Post>>

    @Insert
    fun insertPost(post: Post)

    @Query("SELECT MAX(postId) FROM POSTS")
    fun getPostsNumber(): Int
}