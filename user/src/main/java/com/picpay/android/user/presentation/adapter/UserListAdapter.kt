package com.picpay.android.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.android.user.databinding.ListItemUserBinding
import com.picpay.android.user.usedatasoucer.User

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    var userItemClick: ((User) -> Unit)? = null

    private var users = mutableListOf<User>()


    fun refreshAdapter(users : List<User>){
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder(
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            userItemClick
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}