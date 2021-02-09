package com.example.buryachenko_hw22_arch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.buryachenko_hw22_arch.data.Post
import com.example.buryachenko_hw22_arch.present.PostPresenter
import com.example.buryachenko_hw22_arch.present.PostUIModel
import com.example.buryachenko_hw22_arch.present.PostView
import com.example.buryachenko_hw22_arch.tools.PostComponent
import kotlinx.android.synthetic.main.activity_main.*

class PostActivity : AppCompatActivity(), PostView {

    private val presenter = PostComponent.createPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showPost(post: PostUIModel) {
        titleTextView.text = "post.title"
        descriptionTextView.text = "post.description"
        titleTextView.setTextColor(Color.BLUE)
        descriptionTextView.setTextColor(Color.GREEN)
    }

    override fun showError(error: String) {
        titleTextView.text = error
    }
}