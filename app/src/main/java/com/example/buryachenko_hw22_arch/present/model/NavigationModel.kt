package com.example.buryachenko_hw22_arch.present.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.domain.InputStates
import com.example.buryachenko_hw22_arch.domain.InsertPostUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NavigationModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val insertPostUseCase: InsertPostUseCase
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<PostUIModel>>()
    val reposLiveData: LiveData<List<PostUIModel>> = _reposLiveData

    fun getPostsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPostsUseCase()
            withContext(Dispatchers.Main) {
                if (result.isError) {
                    _reposLiveData.postValue(mutableListOf())
                } else {
                    _reposLiveData.postValue(result.successResult)
                }
            }
        }
    }

    fun updatePostsList(post: StandardPostUIModel): InputStates {
        var state = InputStates.ACCEPTED
        viewModelScope.launch(Dispatchers.IO) {
            state = insertPostUseCase(post)
        }
        Thread.sleep(40)

        if (state == InputStates.ACCEPTED) {
            getPostsList()
        }

        return state
    }

}