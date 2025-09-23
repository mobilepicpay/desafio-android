package com.picpay.desafio.android.presentation.posts.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.Post

class PostListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.name.text = post.title
        binding.username.text = post.body
        binding.progressBar.visibility = android.view.View.GONE
    }
}
