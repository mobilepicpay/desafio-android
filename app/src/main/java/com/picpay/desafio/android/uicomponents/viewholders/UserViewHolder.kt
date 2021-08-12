package com.picpay.desafio.android.uicomponents.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.utils.extensions.loadImage
import com.picpay.desafio.android.utils.pokos.UserViewItem
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserViewHolder(
    parent: ViewGroup,
    view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
) : RecyclerView.ViewHolder(view) {

    private val binding = ListItemUserBinding.bind(view)

    fun bind(userViewItem: UserViewItem) {
        binding.run {
            nameTextView.text = userViewItem.name
            usernameTextView.text = userViewItem.username
            progressBar.visibility = View.VISIBLE

            pictureImageView.loadImage(
                userViewItem.image,
                R.drawable.ic_round_account_circle,
                { itemView.progressBar.visibility = View.GONE },
                { itemView.progressBar.visibility = View.GONE }
            )
        }
    }
}
