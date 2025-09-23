package com.picpay.desafio.android.presentation.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.Post
import com.picpay.desafio.android.presentation.posts.viewholder.PostListItemViewHolder

class PostListAdapter : RecyclerView.Adapter<PostListItemViewHolder>() {

    var posts = emptyList<Post>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                PostDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListItemViewHolder {
        val binding = ListItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListItemViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}
