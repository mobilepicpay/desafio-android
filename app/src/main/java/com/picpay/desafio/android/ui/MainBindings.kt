package com.picpay.desafio.android.ui

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.source.local.UserDb
import com.picpay.desafio.android.data.source.local.toUsersModel
import com.picpay.desafio.android.domain.model.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:items")
fun setItems(rv: RecyclerView, resource: Resource<List<UserDb>>?) {
    resource?.let {
        val list = resource.data.toUsersModel()
        (rv.adapter as UserListAdapter).submitList(list)
    }
}

@BindingAdapter("app:adapter")
fun setAdapter(view: RecyclerView, adapter: ListAdapter<User, UserListAdapter.ViewHolder>) {
    view.adapter = adapter
}

@BindingAdapter("app:loading")
fun setProgress(pb: ProgressBar, resource: Resource<List<UserDb>>?) {
    pb.isVisible = resource is Resource.Loading || resource == null
}

@BindingAdapter("app:imageUrl")
fun setImageView(iv: CircleImageView, url: String) {
    Picasso
        .get()
        .load(url)
        .placeholder(R.drawable.infinitive_progressbar)
        .error(R.drawable.ic_round_account_circle)
        .into(iv)
}