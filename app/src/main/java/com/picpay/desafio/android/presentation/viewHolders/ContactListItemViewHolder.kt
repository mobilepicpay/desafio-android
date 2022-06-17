package com.picpay.desafio.android.presentation.viewHolders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.extensions.toLayoutInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ContactListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contactModel: ContactModel) {
        with(binding) {
            name.text = contactModel.name
            username.text = contactModel.username
            progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(contactModel.image)
                .error(R.drawable.ic_round_account_circle)
                .into(picture, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                    }
                })
        }
    }

    companion object {

        fun newInstance(parent: ViewGroup) = ContactListItemViewHolder(
            ListItemUserBinding.inflate(parent.context.toLayoutInflater(), parent, false)
        )
    }
}