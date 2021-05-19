package com.picpay.desafio.android.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(user: User) {

        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
        val txtUserName = itemView.findViewById<TextView>(R.id.txt_username)

        txtName.text = user.name
        txtUserName.text = user.username

        Picasso.get()
            .load(user.img)
            .into(itemView.img_picture)
    }

}