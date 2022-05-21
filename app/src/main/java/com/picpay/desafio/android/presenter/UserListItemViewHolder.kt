package com.picpay.desafio.android.presenter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val binder = ListItemUserBinding.bind(itemView)

    fun bind(user: User) {
        binder.name.text = user.name
        binder.username.text = user.username
        binder.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binder.picture, object : Callback {
                override fun onSuccess() {
                    binder.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binder.progressBar.visibility = View.GONE
                }
            })
    }
}