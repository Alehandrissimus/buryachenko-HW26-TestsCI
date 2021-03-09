package com.example.buryachenko_hw22_arch

import android.app.Application
import com.example.buryachenko_hw22_arch.di.AppComponent
import com.example.buryachenko_hw22_arch.di.AppModule
import com.example.buryachenko_hw22_arch.di.DaggerAppComponent

class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}