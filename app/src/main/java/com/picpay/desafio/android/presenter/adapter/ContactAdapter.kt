package com.picpay.desafio.android.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.Contact
import com.picpay.desafio.android.presenter.adapter.viewholder.ContactViewHolder

class ContactAdapter : ListAdapter<Contact, ContactViewHolder>(Contact.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
            .let { ContactViewHolder(it) }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}