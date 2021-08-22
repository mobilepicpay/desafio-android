package com.picpay.desafio.android.uicomponents.listUser

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.uicomponents.databinding.ListItemUserBinding

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserDTO) {
        binding.name.text = user.name
        binding.username.text = user.username
        //binding.progressBar.visibility = View.VISIBLE

//        Picasso.get()
//            .load(user.img)
//            .error(R.drawable.ic_round_account_circle)
//            .into(itemView.picture, object : Callback {
//                override fun onSuccess() {
//                    itemView.progressBar.visibility = View.GONE
//                }
//
//                override fun onError(e: Exception?) {
//                    itemView.progressBar.visibility = View.GONE
//                }
//            })
    }
}