package com.example.buryachenko_hw22_arch.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.buryachenko_hw22_arch.postsList.data.PostRepository
import com.example.buryachenko_hw22_arch.postsList.data.api.PostService
import com.example.buryachenko_hw22_arch.postsList.data.UsersStatusedStorage
import com.example.buryachenko_hw22_arch.postsList.data.room.PostsDatabase
import com.example.buryachenko_hw22_arch.postsList.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw22_arch.postsList.domain.PostVerifier
import com.example.buryachenko_hw22_arch.NavigationActivity
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostUIMapper
import com.example.buryachenko_hw22_arch.postsList.ui.PostsListViewModel
import com.example.buryachenko_hw22_arch.tools.ResourceRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
    fun provideViewModel(): PostsListViewModel {
        return ViewModelProviders.of(context as NavigationActivity).get(PostsListViewModel::class.java)
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
    fun providePostVerifier(resourceRepository: ResourceRepository): PostVerifier {
        return PostVerifier(resourceRepository)
    }
}