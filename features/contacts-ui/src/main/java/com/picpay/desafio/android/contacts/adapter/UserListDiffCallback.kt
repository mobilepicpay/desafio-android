package com.picpay.desafio.android.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.shared.model.ViewUser

class UserListDiffCallback(
    private val oldList: List<ViewUser>,
    private val newList: List<ViewUser>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}
