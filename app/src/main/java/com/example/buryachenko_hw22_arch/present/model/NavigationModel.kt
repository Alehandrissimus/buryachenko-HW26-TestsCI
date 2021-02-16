package com.example.buryachenko_hw22_arch.present.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.domain.GetPostsUseCase
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NavigationModel @Inject constructor(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<PostUIModel>>()
    val reposLiveData: LiveData<List<PostUIModel>> = _reposLiveData

    fun getRepoList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = GetPostsUseCase(postRepository, postUIMapper).execute()
            withContext(Dispatchers.Main) {
                if (result.isError) {
                    Log.d("TAG", "Error")
                    _reposLiveData.postValue(mutableListOf())
                } else {
                    _reposLiveData.postValue(result.successResult)
                }
            }
        }
    }

    fun setRepoList(items: List<PostUIModel>) {
        _reposLiveData.postValue(items)
    }

}