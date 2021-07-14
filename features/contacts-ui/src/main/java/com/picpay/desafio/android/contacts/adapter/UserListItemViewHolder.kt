package com.picpay.desafio.android.contacts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contact.R
import com.picpay.desafio.android.contact.databinding.ListItemUserBinding
import com.picpay.desafio.android.shared.extensions.toGone
import com.picpay.desafio.android.shared.extensions.toVisible
import com.picpay.desafio.android.shared.model.ViewUser
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewUser: ViewUser) = binding.apply {
        name.text = viewUser.name
        username.text = viewUser.username
        progressBar.toVisible()

        Picasso.get()
            .load(viewUser.imageUrl)
            .error(R.drawable.ic_round_account_circle)
            .into(picture, object : Callback {
                override fun onSuccess() {
                    progressBar.toGone()
                }

                override fun onError(e: Exception?) {
                    progressBar.toVisible()
                }
            })
    }
}
