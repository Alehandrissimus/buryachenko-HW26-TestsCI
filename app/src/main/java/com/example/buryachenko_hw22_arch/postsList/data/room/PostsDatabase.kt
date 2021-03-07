package com.example.buryachenko_hw22_arch.postsList.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buryachenko_hw22_arch.postsList.data.models.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}