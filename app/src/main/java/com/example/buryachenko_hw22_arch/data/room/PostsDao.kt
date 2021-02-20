package com.example.buryachenko_hw22_arch.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.buryachenko_hw22_arch.data.model.Post

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<Post>

    @Insert
    fun insertPost(post: Post)
}