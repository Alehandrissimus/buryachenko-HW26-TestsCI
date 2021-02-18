package com.example.buryachenko_hw22_arch.present.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buryachenko_hw22_arch.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.domain.InsertPostUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val insertPostUseCase: InsertPostUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NavigationModel::class.java)) {
            return NavigationModel(getPostsUseCase, insertPostUseCase) as T
        }
        throw IllegalStateException("unknown class")
    }
}