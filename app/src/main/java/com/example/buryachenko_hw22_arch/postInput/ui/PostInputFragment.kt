package com.example.buryachenko_hw22_arch.postInput.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.databinding.FragmentPostInputBinding
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import com.example.buryachenko_hw22_arch.navigation.BaseFragment
import com.example.buryachenko_hw22_arch.postsList.ui.PostsListViewModel
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel

class PostInputFragment : BaseFragment(R.layout.fragment_post_input) {

    private val mViewViewModel: PostsListViewModel by activityViewModels()
    private lateinit var binding: FragmentPostInputBinding

    companion object {
        fun newInstance(): PostInputFragment {
            return PostInputFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_input, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.postAddButton.setOnClickListener {
            val status = mViewViewModel.updatePostsList(
                PostUIModel.StandardPostUIModel(
                    postId = -1,
                    userId = "11",
                    title = binding.fragmentInputTitle.text.toString(),
                    body = binding.fragmentInputBody.text.toString(),
                    backgroundColor = ContextCompat.getColor(
                        requireContext(),
                        R.color.design_default_color_background
                    )
                )
            )

            binding.apply {
                when (status) {
                    InputStates.SMALL_TITLE -> fragmentInputErrorText.text =
                        getString(R.string.small_title)
                    InputStates.SMALL_BODY -> fragmentInputErrorText.text =
                        getString(R.string.small_body)
                    InputStates.CONTAINS_BANNED_WORDS -> fragmentInputErrorText.text =
                        getString(R.string.restricted_words)
                    InputStates.BIG_TITLE -> fragmentInputErrorText.text =
                        getString(R.string.big_title)
                    InputStates.BIG_BODY -> fragmentInputErrorText.text =
                        getString(R.string.big_body)
                    InputStates.DECLINED -> fragmentInputErrorText.text = ""
                    else -> navigator.popBackStack()
                }
            }
        }
        binding.postCancelButton.setOnClickListener {
            navigator.popBackStack()
        }
    }
}