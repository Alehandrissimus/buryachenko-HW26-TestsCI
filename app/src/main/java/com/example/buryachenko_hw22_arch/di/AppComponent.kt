package com.example.buryachenko_hw22_arch.di

import com.example.buryachenko_hw22_arch.postInput.ui.PostInputFragment
import com.example.buryachenko_hw22_arch.postsList.ui.PostsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: PostsFragment)
    fun inject(fragment: PostInputFragment)
}