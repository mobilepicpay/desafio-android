package com.picpay.desafio.android.domain.model

import androidx.recyclerview.widget.DiffUtil

data class UserModel(val img: String, val name: String, val username: String) {

    companion object {

        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {

            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}
