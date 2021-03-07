package com.example.buryachenko_hw22_arch.postsList.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsListViewModel @Inject constructor(
        private val getPostsUseCase: GetPostsUseCase,
        private val insertPostUseCase: InsertPostUseCase
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<PostUIModel>>()
    val reposLiveData: LiveData<List<PostUIModel>> = _reposLiveData

    private val _inputLiveData = MutableLiveData<InputStates>()
    val inputLiveData: LiveData<InputStates> = _inputLiveData


    fun getPostsList() {
        viewModelScope.launch {
            val flow = getPostsUseCase.invoke().distinctUntilChanged()
            flow.collect {
                _reposLiveData.postValue(it.successResult)
            }
        }
    }

    fun updatePostsListAsync(post: PostUIModel.StandardPostUIModel) {
        viewModelScope.launch {
            val state = insertPostUseCase.invoke(post)
            _inputLiveData.postValue(state)
            delay(30)
            _inputLiveData.postValue(InputStates.DECLINED)
        }
    }

}