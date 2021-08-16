package com.picpay.desafio.android.user.view.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.user.domain.UserDomain

class UserListBindingHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root), RequestListener<Drawable> {

    fun bind(user: UserDomain) {
        binding.apply {
            name.text = user.name
            username.text = user.userName
            pictureProgressBar.visibility = View.VISIBLE

            Glide
                .with(binding.root.context)
                .load(user.imageUrl)
                .error(R.drawable.ic_round_account_circle)
                .listener(this@UserListBindingHolder)
                .into(picture)
        }
    }

    override fun onLoadFailed(
        exception: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        binding.pictureProgressBar.visibility = View.GONE
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        binding.pictureProgressBar.visibility = View.GONE
        return false
    }
}