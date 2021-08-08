package com.picpay.android.user.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.android.user.databinding.ListItemUserBinding
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.util.loadImage

class UserListItemViewHolder(
    private val itemViewBinding: ListItemUserBinding,
    private val userItemClick: ((User) -> Unit)? = null
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    fun bind(user: User) {

        itemViewBinding.root.setOnClickListener {
            userItemClick?.invoke(user)
        }

        itemViewBinding.name.text = user.name
        itemViewBinding.username.text = user.username
        itemViewBinding.progressBar.visibility = View.VISIBLE
        itemViewBinding.picture.loadImage(user.img, itemViewBinding.progressBar)
    }
}