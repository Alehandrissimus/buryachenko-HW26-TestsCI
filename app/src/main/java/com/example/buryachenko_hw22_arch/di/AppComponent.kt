package com.example.buryachenko_hw22_arch.di

import com.example.buryachenko_hw22_arch.present.NavigationActivity
import com.example.buryachenko_hw22_arch.present.fragments.PostInputFragment
import com.example.buryachenko_hw22_arch.present.fragments.PostsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: NavigationActivity)

}