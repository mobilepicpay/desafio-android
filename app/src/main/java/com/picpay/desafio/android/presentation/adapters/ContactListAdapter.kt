package com.picpay.desafio.android.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.presentation.viewHolders.ContactListItemViewHolder

class ContactListAdapter : ListAdapter<ContactModel, ContactListItemViewHolder>(ContactModel.DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListItemViewHolder {
        return ContactListItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ContactListItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}