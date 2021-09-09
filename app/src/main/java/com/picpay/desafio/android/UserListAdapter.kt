package com.picpay.desafio.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UserListAdapter : ListAdapter<UserResponse, UserListItemViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val viewBinding =
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserListItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class UserDiffCallBack : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean =
        oldItem == newItem
}