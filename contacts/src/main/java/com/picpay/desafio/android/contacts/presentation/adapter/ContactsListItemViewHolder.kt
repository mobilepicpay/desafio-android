package com.picpay.desafio.android.contacts.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.databinding.ListItemContactBinding
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ContactsListItemViewHolder(
    private val binding: ListItemContactBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) {
        binding.name.text = contact.name
        binding.username.text = contact.username
        binding.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(contact.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = View.GONE
                }
            })
    }
}