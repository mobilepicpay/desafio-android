package com.picpay.desafio.android.ui.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.network.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        with(itemView){
            name.text = user.name?: context.getString(R.string.name_not_found)
            username.text = user.username?: context.getString(R.string.username_not_found)
            progressBar.visibility = View.VISIBLE
            setImg(user)
        }
    }

    /**
     * Fun que seta img
     * Se null, seta o placeholder de erro.
     */
    private fun setImg(user: User) {
        if (!user.img.isNullOrEmpty()) {
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture, object : Callback {
                    override fun onSuccess() {
                        itemView.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemView.progressBar.visibility = View.GONE
                    }
                })
        } else {
            //Via picasso, o drawable não estava aparecendo....
//            Picasso.get()
//                .load(R.drawable.ic_round_account_circle)
//                .into(itemView.picture)
            with(itemView){
                val placeholder = context?.getDrawable(R.drawable.ic_round_account_circle)
                picture.setImageDrawable(placeholder)
                progressBar.visibility = View.GONE
            }
        }
    }
}