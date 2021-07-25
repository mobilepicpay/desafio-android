package com.picpay.desafio.android.ui

import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.source.local.UserDb
import com.picpay.desafio.android.data.source.local.toUsersModel
import com.picpay.desafio.android.domain.model.User

@BindingAdapter("app:items")
fun setItems(rv: RecyclerView, resource: Resource<List<UserDb>>?) {
    Log.d("ALEDEV","lista resource $resource")
    resource?.let {
        Log.d("ALEDEV","lista exibir lista $resource")
        val list = resource.data.toUsersModel()
        (rv.adapter as UserListAdapter).submitList(list)
    }
}

@BindingAdapter("app:adapter")
fun setAdapter(view: RecyclerView, adapter: ListAdapter<User, UserListAdapter.ViewHolder>) {
    Log.d("ALEDEV","setar o adapter")
    view.adapter = adapter
}

@BindingAdapter("app:loading")
fun setProgress(pb: ProgressBar, resource: Resource<List<UserDb>>?) {
    Log.d("ALEDEV","sou o progress "+(resource is Resource.Loading || resource == null))
    pb.isVisible = resource is Resource.Loading || resource == null
}