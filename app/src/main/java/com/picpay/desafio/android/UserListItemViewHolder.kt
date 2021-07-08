package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.ui.presentation.UserViewObject
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ListItemUserBinding.bind(itemView)

    fun  bind(user: UserViewObject) {

        binding.name.text = user.name
        binding.username.text = user.userName
        binding.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.imageProfile)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = View.GONE
                }
            })
    }
}