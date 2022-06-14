package com.picpay.desafio.android.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.UserModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(userModel: UserModel) {
        itemView.name.text = userModel.name
        itemView.username.text = userModel.username
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.get()
                .load(userModel.img)
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture, object : Callback {
                    override fun onSuccess() {
                        itemView.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemView.progressBar.visibility = View.GONE
                    }
                })
    }

    companion object {

        fun newInstance(parent: ViewGroup) = UserListItemViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        )
    }
}