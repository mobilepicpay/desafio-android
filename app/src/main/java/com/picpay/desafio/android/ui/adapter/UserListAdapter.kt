package com.picpay.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.repository.model.UserLocal
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val user = differ.currentList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ArticleViewHolder(itemVew: View) : RecyclerView.ViewHolder(itemVew) {

        fun bind(user: UserLocal) {
            showName(user)
            configureImage(user)
        }

        private fun configureImage(user: UserLocal) {
            itemView.progressBar.visibility = View.VISIBLE
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
        }

        private fun showName(user: UserLocal) {
            itemView.name.text = user.name
            itemView.username.text = user.username
        }
    }


    private val differCallback = object : DiffUtil.ItemCallback<UserLocal>() {

        override fun areItemsTheSame(oldItem: UserLocal, newItem: UserLocal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserLocal, newItem: UserLocal): Boolean {
            return oldItem.equals(newItem)
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}
