package com.example.buryachenko_hw22_arch.present.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.databinding.RecycleviewBannedpostBinding
import com.example.buryachenko_hw22_arch.databinding.RecycleviewStandardpostBinding
import com.example.buryachenko_hw22_arch.present.model.PostUIModel

class PostRVAdapter : ListAdapter<PostUIModel, RecyclerView.ViewHolder>(PostRVDiffUtils()) {

    enum class ViewType {
        STANDARD,
        BANNED
    }

    override fun submitList(list: List<PostUIModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PostUIModel.StandardPostUIModel -> ViewType.STANDARD
            is PostUIModel.BannedPostUIModel -> ViewType.BANNED
            else -> throw IllegalArgumentException()
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.STANDARD.ordinal -> {
                PostStandardVH(
                    view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycleview_standardpost, parent, false)
                )
            }
            ViewType.BANNED.ordinal -> {
                PostBannedVH(
                    view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycleview_bannedpost, parent, false)
                )
            }
            else -> throw java.lang.IllegalArgumentException("Illegal viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostStandardVH -> holder.bind(getItem(position) as PostUIModel.StandardPostUIModel)
            is PostBannedVH -> holder.bind(getItem(position) as PostUIModel.BannedPostUIModel)
        }
    }
}

class PostStandardVH(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = DataBindingUtil.bind<RecycleviewStandardpostBinding>(view)

    fun bind(userPost: PostUIModel.StandardPostUIModel) {
        binding?.model = userPost
    }
}

class PostBannedVH(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = DataBindingUtil.bind<RecycleviewBannedpostBinding>(view)

    fun bind(userPost: PostUIModel.BannedPostUIModel) {
        binding?.model = userPost
    }
}