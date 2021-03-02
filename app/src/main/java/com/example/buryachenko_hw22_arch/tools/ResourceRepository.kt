package com.example.buryachenko_hw22_arch.tools

import android.content.Context
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.buryachenko_hw22_arch.postsList.data.PostErrors
import com.example.buryachenko_hw22_arch.postsList.data.api.PostService
import com.example.buryachenko_hw22_arch.postsList.data.models.Post
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ResourceRepository @Inject constructor(
        private val context: Context
) {
    fun getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

    fun getString(@StringRes stringRes: Int) = context.resources.getString(stringRes)

    fun getString(@StringRes stringRes: Int, value: String) =
            context.resources.getString(stringRes, value)
}