package com.picpay.desafio.android.presenter.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ItemContactBinding
import com.picpay.desafio.android.domain.model.Contact
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binder = ItemContactBinding.bind(itemView)

    fun bind(user: Contact) {
        with(binder) {
            name.text = user.name
            username.text = user.username
            Picasso.get()
                .load(user.imageUrl)
                .transform(CropCircleTransformation())
                .placeholder(R.drawable.ic_round_account_circle)
                .error(R.drawable.ic_round_account_circle)
                .into(picture)
        }
    }
}