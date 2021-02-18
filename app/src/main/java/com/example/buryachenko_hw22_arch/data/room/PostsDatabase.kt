package com.example.buryachenko_hw22_arch.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buryachenko_hw22_arch.data.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostsDatabase :RoomDatabase() {
    abstract fun postsDao(): PostsDao
}