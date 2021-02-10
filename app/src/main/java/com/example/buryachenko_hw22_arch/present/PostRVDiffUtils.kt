package com.example.buryachenko_hw22_arch.present

import androidx.recyclerview.widget.DiffUtil

class PostRVDiffUtils : DiffUtil.ItemCallback<PostUIModel>() {
    override fun areItemsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem.equals(newItem)
    }
}