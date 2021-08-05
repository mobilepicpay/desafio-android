package com.picpay.desafio.android.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.common.util.load
import com.picpay.desafio.android.users.repo.UserResponse
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: UserResponse) {
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.visibility = View.VISIBLE
        itemView.picture.load(user.img, object : Callback {
            override fun onSuccess() {
                itemView.progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                itemView.progressBar.visibility = View.GONE
            }
        })
    }
}