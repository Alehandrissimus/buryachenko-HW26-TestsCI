package com.example.buryachenko_hw_22arch.di

import com.example.buryachenko_hw_22arch.postInput.ui.PostInputFragment
import com.example.buryachenko_hw_22arch.postsList.ui.PostsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: PostsFragment)
    fun inject(fragment: PostInputFragment)
}
