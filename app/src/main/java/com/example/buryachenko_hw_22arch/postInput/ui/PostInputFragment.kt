package com.example.buryachenko_hw_22arch.postInput.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.buryachenko_hw_22arch.AppApplication
import com.example.buryachenko_hw_22arch.R
import com.example.buryachenko_hw_22arch.databinding.FragmentPostInputBinding
import com.example.buryachenko_hw_22arch.postsList.domain.InputStates
import com.example.buryachenko_hw_22arch.navigation.BaseFragment
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import javax.inject.Inject

class PostInputFragment : BaseFragment(R.layout.fragment_post_input) {

    @Inject
    lateinit var postInputViewModel: PostInputViewModel

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeCallback()
    }

    private fun observeCallback() {
        postInputViewModel.inputLiveData.observe(viewLifecycleOwner, { state ->
            binding.apply {
                when (state) {
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
                    InputStates.DECLINED -> {}
                    else -> navigator.popBackStack()
                }
            }
            postInputViewModel.cleanLiveData()
        })
    }

    private fun setupListeners() {
        binding.postAddButton.setOnClickListener {
            postInputViewModel.updatePostsListAsync(
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
        }
        binding.postCancelButton.setOnClickListener {
            navigator.popBackStack()
        }
    }
}
