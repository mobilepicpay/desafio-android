package com.picpay.desafio.android

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.domain.models.User

/**
 * Composition/Recomposition and the way the Compose runtime updates the composition tree already solves
 * the diff problem for lists, that's why this class is not needed anymore.
 *
 * I won't delete this as a reminder for the technical interview.
 * */
internal class UserListDiffCallback(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
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
