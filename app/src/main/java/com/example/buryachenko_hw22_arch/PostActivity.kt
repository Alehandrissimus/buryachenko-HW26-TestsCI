package com.example.buryachenko_hw22_arch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.data.PostComponent
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.present.adapter.PostRVAdapter
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.present.PostView
import kotlinx.android.synthetic.main.activity_posts.*

class PostActivity : AppCompatActivity(), PostView {

    private var presenter: PostPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        presenter = PostComponent.createPresenter(this)
        presenter?.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun showError(error: String) {
        postsErrorText.visibility = View.VISIBLE
        postsErrorText.text = error
    }

    override fun setupPostList(list: List<PostUIModel>) {
        setupRecycleView(list)
    }

    private fun setupRecycleView(list: List<PostUIModel>) {
        val adapter = PostRVAdapter()
        postsRecycleView.adapter = adapter
        adapter.submitList(list)

        postsRecycleView.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        postsRecycleView.addItemDecoration(
            DividerItemDecoration(applicationContext, RecyclerView.VERTICAL)
        )
    }
}