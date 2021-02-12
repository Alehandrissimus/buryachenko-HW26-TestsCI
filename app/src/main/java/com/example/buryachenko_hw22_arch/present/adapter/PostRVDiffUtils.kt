package com.example.buryachenko_hw22_arch.present.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.buryachenko_hw22_arch.present.model.PostUIModel

class PostRVDiffUtils : DiffUtil.ItemCallback<PostUIModel>() {
    override fun areItemsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem.equals(newItem)
    }
}