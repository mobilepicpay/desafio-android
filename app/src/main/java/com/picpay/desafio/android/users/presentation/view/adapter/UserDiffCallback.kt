package com.picpay.desafio.android.users.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.users.domain.model.User

object UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}