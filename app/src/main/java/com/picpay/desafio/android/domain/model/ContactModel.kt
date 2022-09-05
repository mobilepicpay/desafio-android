package com.picpay.desafio.android.domain.model

import androidx.recyclerview.widget.DiffUtil

data class ContactModel(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
) {
    companion object {

        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<ContactModel>() {

            override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}