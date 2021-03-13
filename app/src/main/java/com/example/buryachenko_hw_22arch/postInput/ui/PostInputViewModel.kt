package com.example.buryachenko_hw_22arch.postInput.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw_22arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw_22arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw_22arch.postsList.domain.InputStates
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
