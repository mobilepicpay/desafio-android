package com.picpay.desafio.android

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.domain.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val tvName = itemView.findViewById<TextView>(R.id.name)
    val tvUserName = itemView.findViewById<TextView>(R.id.username)
    val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
    val picture = itemView.findViewById<CircleImageView>(R.id.picture)

    fun bind(user: User) {

        tvName.text = user.name
        tvUserName.text = user.username
        progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
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