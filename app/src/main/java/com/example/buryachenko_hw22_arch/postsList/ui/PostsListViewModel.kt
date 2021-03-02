package com.example.buryachenko_hw22_arch.postsList.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.postsList.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.postsList.domain.InputStates
import com.example.buryachenko_hw22_arch.postInput.domain.InsertPostUseCase
import com.example.buryachenko_hw22_arch.postsList.data.models.PostUIModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val insertPostUseCase: InsertPostUseCase
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<PostUIModel>>()
    val reposLiveData: LiveData<List<PostUIModel>> = _reposLiveData

    @SuppressLint("CheckResult")
    fun getPostsList() {
        getPostsUseCase.invoke()
            .observeOn(Schedulers.single())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("TAG", "Subscribtion")
                if (it.isError) {
                    _reposLiveData.postValue(mutableListOf())
                } else {
                    _reposLiveData.postValue(it.successResult)
                }
            }
    }

    @SuppressLint("CheckResult")
    fun updatePostsList(post: PostUIModel.StandardPostUIModel): InputStates {
        var state = InputStates.DECLINED
        insertPostUseCase.invoke(post)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                state = it
                Log.d("TAG", state.toString())
                if (state == InputStates.ACCEPTED) {
                    //getPostsList()
                }
            }, {
                Log.d("TAG", "insertingPost thrown: $it")
            })

        return state
    }

}