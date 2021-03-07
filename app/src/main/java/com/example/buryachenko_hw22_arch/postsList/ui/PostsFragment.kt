package com.example.buryachenko_hw22_arch.postsList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.databinding.FragmentPostsBinding
import com.example.buryachenko_hw22_arch.navigation.BaseFragment

class PostsFragment : BaseFragment(R.layout.fragment_posts) {

    private val viewModel: PostsListViewModel by activityViewModels()
    private val RVadapter = PostRVAdapter()
    private lateinit var binding: FragmentPostsBinding

    companion object {
        fun newInstance(): PostsFragment {
            return PostsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupRecycleView()
        observePostsRepo()
    }

    private fun setupListeners() {
        binding.postsImageButtonAdd.setOnClickListener {
            navigator.showPostInputFragment()
        }
    }

    private fun setupRecycleView() {
        binding.postsRecycleView.apply {
            adapter = RVadapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL)
            )
        }
    }

    private fun observePostsRepo() {
        viewModel.reposLiveData.observe(viewLifecycleOwner, { list ->
            RVadapter.submitList(list)
            Thread.sleep(40)
            binding.postsRecycleView.scrollToPosition(0)
        })
    }
}