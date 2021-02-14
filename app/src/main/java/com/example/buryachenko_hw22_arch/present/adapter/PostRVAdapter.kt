package com.example.buryachenko_hw22_arch.present.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.present.model.BannedPostUIModel
import com.example.buryachenko_hw22_arch.present.model.PostUIModel
import com.example.buryachenko_hw22_arch.present.model.StandardPostUIModel
import kotlinx.android.synthetic.main.recycleview_bannedpost.view.*
import kotlinx.android.synthetic.main.recycleview_standardpost.view.*

class PostRVAdapter : ListAdapter<PostUIModel, RecyclerView.ViewHolder>(PostRVDiffUtils()) {

    enum class ViewType {
        STANDARD,
        BANNED
    }

    override fun submitList(list: List<PostUIModel>?) {
        super.submitList(list?.let{ArrayList(it)} )
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is StandardPostUIModel -> ViewType.STANDARD
            is BannedPostUIModel -> ViewType.BANNED
            else -> throw IllegalArgumentException()
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.STANDARD.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_standardpost, parent, false)

            PostStandardVH(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_bannedpost, parent, false)

            PostBannedVH(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostStandardVH) {
            holder.bind(getItem(position) as StandardPostUIModel)
        } else if (holder is PostBannedVH) {
            holder.bind(getItem(position) as BannedPostUIModel)
        }
    }
}

class PostStandardVH(view: View) : RecyclerView.ViewHolder(view) {


    fun bind(userPost: StandardPostUIModel) {
        itemView.recycleStandardTextTitle.text = userPost.title
        itemView.recycleStandardTextUser.text = userPost.userId
        itemView.recycleStandardTextBody.text = userPost.body
        itemView.recycleStandardContainer.setBackgroundColor(userPost.backgroundColor)
    }
}

class PostBannedVH(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(post: BannedPostUIModel) {
        itemView.recycleBannedText.text = post.userId
    }
}