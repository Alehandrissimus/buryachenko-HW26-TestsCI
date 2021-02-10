package com.example.buryachenko_hw22_arch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.present.PostRVAdapter
import com.example.buryachenko_hw22_arch.present.PostView
import com.example.buryachenko_hw22_arch.data.PostComponent
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
        titleTextView.text = error
    }

    override fun setupPostList(list: List<PostModel>) {
        setupRecycleView(list)
    }

    private fun setupRecycleView(list: List<PostModel>) {
        postsRecycleView.adapter = PostRVAdapter(list)
        postsRecycleView.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        postsRecycleView.addItemDecoration(
            DividerItemDecoration(applicationContext, RecyclerView.VERTICAL)
        )
    }
}