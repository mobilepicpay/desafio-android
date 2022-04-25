package com.picpay.desafio.android.contacts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.databinding.ListItemContactBinding
import com.picpay.desafio.android.contacts.domain.model.Contact

class ContactsListAdapter : RecyclerView.Adapter<ContactsListItemViewHolder>() {

    private val items = mutableListOf<Contact>()

    override fun getItemCount(): Int {
        return items.size
    }

    companion object Diff : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListItemViewHolder {
        val view = ListItemContactBinding.inflate(LayoutInflater.from(parent.context))
        return ContactsListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsListItemViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    fun submitList(list: List<Contact>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}