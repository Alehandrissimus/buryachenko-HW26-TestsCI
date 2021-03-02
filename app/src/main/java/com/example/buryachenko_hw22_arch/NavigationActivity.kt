package com.example.buryachenko_hw22_arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.buryachenko_hw22_arch.di.AppModule
import com.example.buryachenko_hw22_arch.di.DaggerAppComponent
import com.example.buryachenko_hw22_arch.navigation.Navigator
import com.example.buryachenko_hw22_arch.postsList.ui.PostsListViewModel
import com.example.buryachenko_hw22_arch.di.ViewModelFactory
import javax.inject.Inject

class NavigationActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mPostsListViewModel: PostsListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[PostsListViewModel::class.java]
    }

    val navigator by lazy { Navigator(supportFragmentManager, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        initInjector()

        mPostsListViewModel.getPostsList()
        navigator.showPostsFragment()
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }
}

