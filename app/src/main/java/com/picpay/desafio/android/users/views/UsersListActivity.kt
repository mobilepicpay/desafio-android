package com.picpay.desafio.android.users.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.common.LoadState
import com.picpay.desafio.android.common.LoadState.SUCCESS
import com.picpay.desafio.android.databinding.ActivityUsersListBinding
import com.picpay.desafio.android.users.UserListAdapter
import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.viewmodels.UsersListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class UsersListActivity : AppCompatActivity() {

    private val usersListViewModel: UsersListViewModel by viewModel()
    private val userListAdapter: UserListAdapter by inject()

    private lateinit var binding: ActivityUsersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = userListAdapter

        registerObservers()

        usersListViewModel.loadUsers()
    }

    @SuppressWarnings("Unchecked cast")
    private fun registerObservers() {
        usersListViewModel.usersListState.observe(this, {
            when (it) {
                LoadState.ERROR -> handleError()

                is SUCCESS -> populateUsers(it.data as List<UserResponse>)

                else -> binding.userListProgressBar.isVisible = true
            }
        })
    }

    private fun handleError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.isVisible = false
        binding.recyclerView.isVisible = false

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun populateUsers(users: List<UserResponse>) {
        binding.userListProgressBar.isVisible = false
        userListAdapter.users = users
    }
}
