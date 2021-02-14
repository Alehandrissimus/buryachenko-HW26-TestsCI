package com.example.buryachenko_hw22_arch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.buryachenko_hw22_arch.data.PostComponent
import com.example.buryachenko_hw22_arch.present.NavigationModel
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.present.PostView

class  NavigationActivity : AppCompatActivity() {

    private val viewModel: NavigationModel by viewModels()
    val navigator by lazy { Navigator(supportFragmentManager, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        observePostsRepo()
        viewModel.getRepoList(applicationContext)
        navigator.showPostsFragment()
        //viewModel.reposLiveData.value?.let { navigator.showPostsFragment(it) }
    }

    override fun onBackPressed() {
        navigator.popBackStack()
    }

    private fun observePostsRepo() {
        viewModel.reposLiveData.observe(this, Observer { list ->
            Log.d("TAG", "observer ${list.first()}")
            setupPostList(list)
        })
    }

    private fun setupPostList(items: List<PostUIModel>) {

    }
}