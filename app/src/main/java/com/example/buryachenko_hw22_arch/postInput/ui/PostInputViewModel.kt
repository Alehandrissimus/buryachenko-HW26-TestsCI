package com.example.buryachenko_hw22_arch.postInput.ui

import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostInputViewModel @Inject constructor(
        private val insertPostUseCase: InsertPostUseCase
) {
    /*
    fun updatePostsList(post: PostUIModel.StandardPostUIModel): InputStates {
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

     */
}