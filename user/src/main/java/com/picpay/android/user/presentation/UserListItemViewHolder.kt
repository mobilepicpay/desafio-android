package com.picpay.android.user.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.android.user.api.model.User


class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
//        itemView.name.text = user.name
//        itemView.username.text = user.username
//        itemView.progressBar.visibility = View.VISIBLE
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