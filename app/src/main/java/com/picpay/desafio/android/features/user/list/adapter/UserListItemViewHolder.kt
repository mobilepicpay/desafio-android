package com.picpay.desafio.android.features.user.list.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.utils.ImageHelper
import com.picpay.desafio.domain.models.User
import kotlinx.android.synthetic.main.list_item_user.view.*
import kotlin.reflect.KFunction2

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        user: User,
        onItemListClick: KFunction2<User, ImageView, Unit>?
    ) {
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.visibility = View.VISIBLE
        itemView.picture.apply {
            transitionName = user.img
            ImageHelper.downloadImage(this, user.img, ::onImageDownloadComplete)
        }

        onItemListClick?.run {
            itemView.setOnClickListener { onItemListClick(user, itemView.picture) }
        }
    }

    private fun onImageDownloadComplete() {
        itemView.progressBar.visibility = View.GONE
    }
}