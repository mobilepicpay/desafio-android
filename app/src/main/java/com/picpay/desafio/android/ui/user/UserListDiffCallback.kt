package com.picpay.desafio.android.ui.user


import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.entities.UsersDomain

class UserListDiffCallback(
    private val oldList: List<UsersDomain>,
    private val newList: List<UsersDomain>
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