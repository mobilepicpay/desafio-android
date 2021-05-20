package com.picpay.desafio.android.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(user: User) {

        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
        val txtUserName = itemView.findViewById<TextView>(R.id.txt_username)
        val imgUrl = itemView.findViewById<ImageView>(R.id.img_picture)

        txtName.text = user.name
        txtUserName.text = user.userName
        imgUrl.load(user.imgUrl) {
            transformations(RoundedCornersTransformation())
            fallback(R.drawable.ic_round_account_circle)

            listener(onStart = {
                itemView.progressBar.isVisible = true
            }, onSuccess = { _, _ ->
                itemView.progressBar.isVisible = false
            }, onError = { _, _ ->
                itemView.progressBar.isVisible = false
            })
        }
    }
}