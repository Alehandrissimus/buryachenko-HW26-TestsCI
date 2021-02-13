package com.example.buryachenko_hw22_arch.present.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.android.parcel.Parcelize

abstract class PostUIModel

data class StandardPostUIModel(
    val title: String,
    val userId: String,
    val body: String,
    @ColorInt val backgroundColor: Int
) : PostUIModel()

data class BannedPostUIModel(
    val userId: String
) : PostUIModel()