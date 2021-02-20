package com.example.buryachenko_hw22_arch.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.data.PostService
import com.example.buryachenko_hw22_arch.data.UsersStatusedStorage
import com.example.buryachenko_hw22_arch.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.domain.PostVerifier
import com.example.buryachenko_hw22_arch.present.NavigationActivity
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import com.example.buryachenko_hw22_arch.present.ResourceRepository
import com.example.buryachenko_hw22_arch.present.model.NavigationModel
import com.example.buryachenko_hw22_arch.present.model.ViewModelFactory
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
        postService: PostService
    ): PostRepository {
        return PostRepository(database, postService, postMapper)
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
    @Singleton
    fun provideViewModelFactory(
        getPostsUseCase: GetPostsUseCase,
        insertPostUseCase: InsertPostUseCase
    ): ViewModelFactory {
        return ViewModelFactory(getPostsUseCase, insertPostUseCase)
    }

    @Provides
    fun provideViewModel(): NavigationModel {
        return ViewModelProviders.of(context as NavigationActivity).get(NavigationModel::class.java)
    }

    @Provides
    fun provideGetPostsUseCase(
        postRepository: PostRepository,
        postUIMapper: PostUIMapper
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
    fun providePostVerifier(): PostVerifier {
        return PostVerifier()
    }
}