package com.picpay.desafio.android.features.user.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.room.models.User
import com.picpay.desafio.android.utils.ImageHelper
import kotlinx.android.synthetic.main.list_item_user.view.*
import kotlin.reflect.KFunction1

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        user: User,
        onItemListClick: KFunction1<Int, Unit>?
    ) {
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.visibility = View.VISIBLE

        onItemListClick?.run {
            itemView.setOnClickListener{this(user.id)}
        }

        ImageHelper.downloadImage(itemView.picture, user.img, ::onImageDownloadComplete)
    }

    private fun onImageDownloadComplete() {
        itemView.progressBar.visibility = View.GONE
    }
}