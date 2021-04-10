package com.picpay.desafio.android.feature.home.ui.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.picpay.desafio.android.feature.home.R
import com.picpay.desafio.android.feature.home.databinding.ListItemUserBinding
import com.picpay.desafio.android.feature.home.interactor.user.UserEntity

class UserListItemViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageLoadListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            binding.progressBar.visibility = View.GONE
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            binding.progressBar.visibility = View.GONE
            return false
        }

    }

    fun bind(user: UserEntity) {
        binding.name.text = user.name
        binding.username.text = user.username
        binding.progressBar.visibility = View.VISIBLE

        Glide.with(itemView)
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .addListener(imageLoadListener)
            .into(binding.picture)
    }
}