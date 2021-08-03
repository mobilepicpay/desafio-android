package com.picpay.android.user.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.android.user.R
import com.picpay.android.user.databinding.ListItemUserBinding
import com.picpay.android.user.usedatasoucer.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

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
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemViewBinding.picture, object : Callback {
                override fun onSuccess() {
                    itemViewBinding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemViewBinding.progressBar.visibility = View.GONE
                }
            })
    }
}