package com.example.buryachenko_hw22_arch.present

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buryachenko_hw22_arch.R
import com.example.buryachenko_hw22_arch.domain.BannedUserPostModel
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.domain.StandardUserPostModel
import kotlinx.android.synthetic.main.recycleview_bannedpost.view.*
import kotlinx.android.synthetic.main.recycleview_standardpost.view.*

class PostRVAdapter(private val items: List<PostUIModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        STANDARD,
        BANNED
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is StandardPostUIModel -> ViewType.STANDARD
            is BannedPostUIModel -> ViewType.BANNED
            else -> throw IllegalArgumentException()
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ViewType.STANDARD.ordinal) {
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
        if(holder is PostStandardVH) {
            holder.bind(items[position] as StandardPostUIModel)
        } else if (holder is PostBannedVH) {
            holder.bind(items[position] as BannedPostUIModel)
        }
    }

    override fun getItemCount() = items.size

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