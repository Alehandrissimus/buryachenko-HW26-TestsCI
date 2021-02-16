package com.example.buryachenko_hw22_arch.present.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.present.model.NavigationModel
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.present.model.StandardPostUIModel
import kotlinx.android.synthetic.main.fragment_post_input.*

class PostInputFragment : BaseFragment(R.layout.fragment_post_input) {

    private val viewModel: NavigationModel by activityViewModels()

    companion object {
        fun newInstance() : PostInputFragment {
            return PostInputFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        postAddButton.setOnClickListener {
            val items = viewModel.reposLiveData.value as MutableList<PostUIModel>
            items.add(0, StandardPostUIModel(
                userId = "11",
                title = fragmentInputTitle.text.toString(),
                body = fragmentInputBody.text.toString(),
                backgroundColor = ContextCompat.getColor(requireContext(), R.color.design_default_color_background)
            ))
            viewModel.setRepoList(items)
            navigator.popBackStack()
        }
        postCancelButton.setOnClickListener {
            navigator.popBackStack()
        }
    }
}