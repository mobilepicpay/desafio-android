package com.picpay.desafio.android

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.user.network.UserResponse

class UserListDiffCallback(
    private val oldList: List<UserResponse>,
    private val newList: List<UserResponse>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}