package com.picpay.desafio.android.user.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.user.domain.UserDomain

class UserListDiffCallback(
    private val oldList: List<UserDomain>,
    private val newList: List<UserDomain>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldList[oldItemPosition] == newList[newItemPosition]
}