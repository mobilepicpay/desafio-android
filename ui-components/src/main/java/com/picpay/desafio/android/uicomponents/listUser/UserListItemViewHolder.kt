package com.picpay.desafio.android.uicomponents.listUser

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.uicomponents.databinding.ListItemUserBinding
import com.picpay.desafio.android.util.loadImage

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserDTO) {
        binding.apply {
            name.text = user.name
            username.text = user.username
            progressBar.visibility = View.VISIBLE
            picture.loadImage(user.img) {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}