package com.example.buryachenko_hw22_arch.tools

import com.example.buryachenko_hw22_arch.present.NavigationActivity
import com.example.buryachenko_hw22_arch.tools.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: NavigationActivity)

}