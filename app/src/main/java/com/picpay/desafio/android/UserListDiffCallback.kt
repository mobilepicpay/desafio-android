package com.picpay.desafio.android

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.ui.presentation.UserViewObject

class UserListDiffCallback(
    private val oldList: List<UserViewObject>,
    private val newList: List<UserViewObject>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userName == newList[newItemPosition].userName
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