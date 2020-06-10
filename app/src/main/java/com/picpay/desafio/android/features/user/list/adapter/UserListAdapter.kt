package com.picpay.desafio.android.features.user.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.domain.models.User
import kotlin.reflect.KFunction2

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    private var onItemListClick: KFunction2<User, ImageView, Unit>? = null
    private var users = emptyList<User>()
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

    fun setData(users: List<User>, onItemListClick: KFunction2<User, ImageView, Unit>) {
        this.users = users
        this.onItemListClick = onItemListClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)
        return UserListItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) =
        holder.bind(users[position], onItemListClick)

    override fun getItemCount(): Int = users.size
}