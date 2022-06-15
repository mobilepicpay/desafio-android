package com.picpay.desafio.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.data.response.User
import com.picpay.desafio.android.view.viewHolder.UserListItemViewHolder

class UserListAdapter(private var results: List<User>) : RecyclerView.Adapter<UserListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }
}