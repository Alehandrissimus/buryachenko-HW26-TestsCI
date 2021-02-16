package com.example.buryachenko_hw22_arch.present.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper
): ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NavigationModel::class.java)) {
            return NavigationModel(postRepository, postUIMapper) as T
        }
        throw IllegalStateException("unknown class")
    }
}