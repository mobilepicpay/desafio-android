package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.user.network.UserResponse
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListBindingHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userResponse: UserResponse) {
        binding.apply {
            name.text = userResponse.name
            username.text = userResponse.username
            progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(userResponse.img)
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