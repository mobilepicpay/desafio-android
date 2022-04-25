package com.picpay.desafio.android.contacts.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_contact.view.*

class ContactsListItemViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(contact: Contact) {
        view.name.text = contact.name
        view.username.text = contact.username
        view.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(contact.img)
            .error(R.drawable.ic_round_account_circle)
            .into(view.picture, object : Callback {
                override fun onSuccess() {
                    view.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    view.progressBar.visibility = View.GONE
                }
            })
    }
}