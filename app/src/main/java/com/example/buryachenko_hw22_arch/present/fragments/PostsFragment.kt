package com.example.buryachenko_hw22_arch.present.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.present.model.NavigationModel
import com.example.buryachenko_hw22_arch.present.adapter.PostRVAdapter
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : BaseFragment(R.layout.fragment_posts) {

    private val viewModel: NavigationModel by activityViewModels()
    private val adapter = PostRVAdapter()

    companion object {
        fun newInstance(): PostsFragment {
            return PostsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupRecycleView()
        observePostsRepo()
    }

    private fun setupListeners() {
        postsImageButtonAdd.setOnClickListener {
            navigator.showPostInputFragment()
        }
    }

    private fun setupRecycleView() {
        postsRecycleView.adapter = adapter
        postsRecycleView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        postsRecycleView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )
    }

    private fun observePostsRepo() {
        viewModel.reposLiveData.observe(viewLifecycleOwner,  { list ->
            adapter.submitList(list)
            postsRecycleView.post { postsRecycleView.scrollToPosition(0) }
        })
    }
}