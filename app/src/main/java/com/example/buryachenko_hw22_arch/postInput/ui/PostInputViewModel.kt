package com.example.buryachenko_hw22_arch.postInput.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostInputViewModel @Inject constructor(
    private val insertPostUseCase: InsertPostUseCase,
) : ViewModel() {

    private val _inputLiveData = MutableLiveData<InputStates>()
    val inputLiveData: LiveData<InputStates> = _inputLiveData

    fun updatePostsListAsync(post: PostUIModel.StandardPostUIModel) {
        viewModelScope.launch {
            val state = insertPostUseCase.invoke(post)
            _inputLiveData.postValue(state)
        }
    }

    fun cleanLiveData() {
        _inputLiveData.postValue(InputStates.DECLINED)
    }
}