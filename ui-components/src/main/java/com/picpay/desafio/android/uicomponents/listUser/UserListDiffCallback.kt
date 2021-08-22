package com.picpay.desafio.android.uicomponents.listUser

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.model.UserDTO

class UserListDiffCallback(
    private val oldList: List<UserDTO>,
    private val newList: List<UserDTO>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}