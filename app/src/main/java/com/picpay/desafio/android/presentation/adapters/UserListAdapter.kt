package com.picpay.desafio.android.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.presentation.viewHolders.UserListItemViewHolder

class UserListAdapter : ListAdapter<UserModel, UserListItemViewHolder>(UserModel.DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}