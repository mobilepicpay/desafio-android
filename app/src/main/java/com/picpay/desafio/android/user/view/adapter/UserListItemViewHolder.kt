package com.picpay.desafio.android.user.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.user.domain.UserDomain
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListBindingHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserDomain) {
        binding.apply {
            name.text = user.name
            username.text = user.userName
            progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(user.imageUrl)
                .error(R.drawable.ic_round_account_circle)
                .into(picture, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                    }
                })
        }
    }
}