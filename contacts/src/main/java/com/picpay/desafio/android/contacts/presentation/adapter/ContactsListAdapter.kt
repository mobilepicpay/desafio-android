package com.picpay.desafio.android.contacts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.databinding.ListItemContactBinding
import com.picpay.desafio.android.contacts.domain.model.Contact

class ContactsListAdapter : RecyclerView.Adapter<ContactsListItemViewHolder>() {

    var contacts = emptyList<Contact>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ContactsListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListItemViewHolder {
        val view = ListItemContactBinding.inflate(LayoutInflater.from(parent.context))
        return ContactsListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsListItemViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size
}