package com.picpay.desafio.android.users.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.common.LoadState
import com.picpay.desafio.android.common.LoadState.SUCCESS
import com.picpay.desafio.android.users.UserListAdapter
import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.viewmodels.UsersListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UsersListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val usersListViewModel: UsersListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        progressBar.visibility = View.VISIBLE

        registerObservers()

        usersListViewModel.loadUsers()
    }

    private fun registerObservers() {
        usersListViewModel.usersListState.observe(this, Observer {
            when (it) {
                LoadState.ERROR -> {
                    handleError()
                }

                is SUCCESS -> {
                    populateUsers(it.data as List<UserResponse>)
                }
            }
        })
    }

    private fun handleError() {
        val message = getString(R.string.error)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun populateUsers(users: List<UserResponse>) {
        progressBar.visibility = View.GONE
        adapter.users = users
    }
}
