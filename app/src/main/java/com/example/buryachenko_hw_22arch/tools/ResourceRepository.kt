package com.example.buryachenko_hw_22arch.tools

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceRepository @Inject constructor(
        private val context: Context
) {
    fun getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

    fun getString(@StringRes stringRes: Int) = context.resources.getString(stringRes)

    fun getString(@StringRes stringRes: Int, value: String) =
            context.resources.getString(stringRes, value)
}
