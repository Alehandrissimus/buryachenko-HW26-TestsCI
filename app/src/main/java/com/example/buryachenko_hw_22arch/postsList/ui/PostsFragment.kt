package com.example.buryachenko_hw_22arch.postsList.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw_22arch.AppApplication
import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.databinding.FragmentPostsBinding
import com.example.buryachenko_hw_22arch.navigation.BaseFragment
import javax.inject.Inject

class PostsFragment : BaseFragment(R.layout.fragment_posts) {

    @Inject
    lateinit var postsViewModel: PostsListViewModel

    private val rvAdapter = PostRVAdapter()
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
        observePostsErrors()
        observePostsRepo()
        postsViewModel.subscribeOnDatabase()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppApplication).appComponent.inject(this)
    }

    private fun setupListeners() {
        binding.postsImageButtonAdd.setOnClickListener {
            navigator.showPostInputFragment()
        }
    }

    private fun setupRecycleView() {
        binding.postsRecycleView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL)
            )
        }
    }

    private fun observePostsRepo() {
        postsViewModel.reposLiveData.observe(viewLifecycleOwner, { list ->
            rvAdapter.submitList(list)
            binding.postsRecycleView.scrollToPosition(0)
        })
    }

    private fun observePostsErrors() {
        postsViewModel.errorLiveData.observe(viewLifecycleOwner, { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        })
    }
}
