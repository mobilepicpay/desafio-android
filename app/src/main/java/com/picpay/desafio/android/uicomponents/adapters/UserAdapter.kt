package com.picpay.desafio.android.uicomponents.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.uicomponents.viewholders.UserViewHolder
import com.picpay.desafio.android.utils.UserListDiffCallback
import com.picpay.desafio.android.utils.pokos.UserViewItem

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var userViewItems = emptyList<UserViewItem>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userViewItems[position])
    }

    override fun getItemCount(): Int = userViewItems.size
}
