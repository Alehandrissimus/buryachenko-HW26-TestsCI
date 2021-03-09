package com.example.buryachenko_hw22_arch.postsList.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.postsList.data.PostRepository
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.GetPostsUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PostsListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val postRepository: PostRepository,
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<PostUIModel>>()
    val reposLiveData: LiveData<List<PostUIModel>> = _reposLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val postsScope = CoroutineScope(
        SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
        _errorLiveData.postValue("Error: $throwable")
    })

    fun subscribeOnDatabase() {
        postsScope.launch {
            postRepository.loadBackPosts()
        }
        postsScope.launch {
            val flow = getPostsUseCase.invoke().distinctUntilChanged()
            flow.collect {
                _reposLiveData.postValue(it.successResult)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        postsScope.cancel()
    }
}