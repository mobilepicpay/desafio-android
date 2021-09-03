package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val viewBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(user: User) {
        viewBinding.name.text = user.name
        viewBinding.username.text = user.username
        viewBinding.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(viewBinding.picture, object : Callback {
                override fun onSuccess() {
                    viewBinding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    viewBinding.progressBar.visibility = View.GONE
                }
            })
    }
}