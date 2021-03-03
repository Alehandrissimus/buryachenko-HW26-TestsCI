package com.example.buryachenko_hw22_arch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buryachenko_hw22_arch.postsList.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.postsList.data.PostRepository
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostMapper
import com.example.buryachenko_hw22_arch.postsList.data.mappers.PostUIMapper
import com.example.buryachenko_hw22_arch.postsList.ui.PostsListViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
        private val getPostsUseCase: GetPostsUseCase,
        private val insertPostUseCase: InsertPostUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsListViewModel::class.java)) {
            return PostsListViewModel(getPostsUseCase, insertPostUseCase) as T
        }
        throw IllegalStateException("unknown class")
    }
}