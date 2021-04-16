package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val itemBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: User) {
        itemBinding.name.text = user.name
        itemBinding.username.text = user.username
        itemBinding.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemBinding.picture, object : Callback {
                override fun onSuccess() {
                    itemBinding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemBinding.progressBar.visibility = View.GONE
                }
            })
    }
}