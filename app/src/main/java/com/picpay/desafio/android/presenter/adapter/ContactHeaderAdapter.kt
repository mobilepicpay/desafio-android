package com.picpay.desafio.android.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presenter.adapter.viewholder.ContactHeaderViewHolder

class ContactHeaderAdapter : RecyclerView.Adapter<ContactHeaderViewHolder>() {

    override fun getItemCount() = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact_header, parent, false)
            .let { ContactHeaderViewHolder(it) }

    override fun onBindViewHolder(holder: ContactHeaderViewHolder, position: Int) {
        // don't need to bind anything
    }

}