package com.example.buryachenko_hw_22arch.di

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.buryachenko_hw_22arch.postsList.data.PostRepository
import com.example.buryachenko_hw_22arch.postsList.data.api.PostService
import com.example.buryachenko_hw_22arch.postsList.data.UsersStatusedStorage
import com.example.buryachenko_hw_22arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw_22arch.postsList.domain.GetPostsUseCase
import com.example.buryachenko_hw_22arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw_22arch.postsList.domain.PostVerifier
import com.example.buryachenko_hw_22arch.postsList.data.mappers.PostDbMapper
import com.example.buryachenko_hw_22arch.postsList.data.mappers.PostUIMapper
import com.example.buryachenko_hw_22arch.tools.ResourceRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@SuppressLint("TooManyFunctions")
@Module
class AppModule(private var context: Context) {

    @Provides
    @Singleton
    @NonNull
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
        )
    }


    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
            database: PostsDatabase,
            postMapper: PostMapper,
            postService: PostService,
    ): PostRepository {
        return PostRepository(database, postService, postMapper)
    }

    @Provides
    fun providePostDbMapper(database: PostsDatabase): PostDbMapper {
        return PostDbMapper(database)
    }

    @Provides
    fun providePostMapper(usersStatusedStorage: UsersStatusedStorage): PostMapper {
        return PostMapper(usersStatusedStorage)
    }

    @Provides
    @Singleton
    fun provideUsersStatusedStorage(): UsersStatusedStorage {
        return UsersStatusedStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideResourceRepository(context: Context): ResourceRepository {
        return ResourceRepository(context)
    }

    @Provides
    fun provideGetPostsUseCase(
            postRepository: PostRepository,
            postUIMapper: PostUIMapper,
    ): GetPostsUseCase {
        return GetPostsUseCase(postRepository, postUIMapper)
    }

    @Provides
    @Singleton
    fun provideDatabase(): PostsDatabase {
        return Room.databaseBuilder(
                context,
                PostsDatabase::class.java, "database"
        ).build()
    }

    @Provides
    fun providePostVerifier(resourceRepository: ResourceRepository): PostVerifier {
        return PostVerifier(resourceRepository)
    }
}
