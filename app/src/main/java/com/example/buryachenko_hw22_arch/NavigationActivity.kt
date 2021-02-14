package com.example.buryachenko_hw22_arch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.buryachenko_hw22_arch.data.PostComponent
import com.example.buryachenko_hw22_arch.present.NavigationModel
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.present.PostView

class  NavigationActivity : AppCompatActivity(), PostView {

    private val viewModel: NavigationModel by viewModels()
    val navigator by lazy { Navigator(supportFragmentManager, R.id.container) }
    private var presenter: PostPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        presenter = PostComponent.createPresenter(this)
        presenter?.attachView(this)

    }

    override fun onBackPressed() {
        navigator.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun setupPostList(list: List<PostUIModel>) {
        navigator.showPostsFragment(list)
    }

    private fun observePostsRepo() {
        viewModel.reposLiveData.observe(this, Observer {
            //setupPostList(it)
        })
    }
}