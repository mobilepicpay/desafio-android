package com.picpay.desafio.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.commons.extensions.showToast
import com.picpay.desafio.android.commons.extensions.visible
import com.picpay.desafio.android.commons.util.NetworkUtil
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.state.UserState
import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModel()
    private var binding: ActivityMainBinding? = null
    private lateinit var usersAdapter: UserListAdapter
    private var isConnected: Boolean = false
        get() = NetworkUtil.isNetworkConnected(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupFields()
        setupObservers()
        getUsers()
    }

    private fun setupFields() {
        binding?.apply {
            setupRecycler()
            ivRefresh.setOnClickListener { userViewModel.getUsers(isConnected, true) }
        }
    }

    private fun setupObservers() {
        userViewModel.state.observe(this) { state ->
            when (state) {
                is UserState.ShowLoading -> {
                    showLoading(state.show)
                }
                is UserState.Error -> {
                    showToast(getString(R.string.error))
                    hideRecyclerView()
                }

                is UserState.ShowUserList -> {
                    usersAdapter.users = state.userList
                    showRecyclerView()
                }
            }
        }
    }

    private fun hideRecyclerView() {
        binding?.recyclerView?.visible(false)
    }

    private fun showRecyclerView() {
        binding?.recyclerView?.visible(true)
    }

    private fun getUsers() {
        userViewModel.getUsers(isConnected, false)
    }

    private fun showLoading(show: Boolean) {
        binding?.apply {
            userListProgressBar.visible(show)
        }
    }

    private fun setupRecycler() {
        binding?.apply {
            usersAdapter = UserListAdapter()
            recyclerView.apply {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }
}
