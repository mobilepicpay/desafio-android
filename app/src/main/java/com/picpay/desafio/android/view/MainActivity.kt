package com.picpay.desafio.android.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.viewmodel.UserListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userListViewModel: UserListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userListViewModel = ViewModelProvider.NewInstanceFactory().create(UserListViewModel::class.java)
        userListViewModel.init()
        initObserver()
        loadingVisibility(true)
    }

    private fun initObserver() {
        userListViewModel.usersList.observe(this) { list ->
            if (list.isNotEmpty()) {
                populateList(list)
                loadingVisibility(false)
            }
        }
    }

    private fun populateList(list: List<User>) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userListViewModel.usersList.apply {
            recyclerView.hasFixedSize()
            adapter = UserListAdapter(list)
            recyclerView.adapter = adapter
        }
    }

    private fun loadingVisibility(isLoading: Boolean) {
        progressBar = findViewById(R.id.user_list_progress_bar)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
