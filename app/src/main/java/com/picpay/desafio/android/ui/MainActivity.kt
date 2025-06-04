package com.picpay.desafio.android.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.UiState
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModel()

    private val adapter by lazy { UserListAdapter() }
    private val layoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSwipeToRefresh()
        observeUiState()
        viewModel.loadUsers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = this@MainActivity.layoutManager
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshUsers()
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is UiState.Loading -> showLoading()
                    is UiState.Success -> showSuccess(state.users)
                    is UiState.Error -> showError(state.message)
                }
            }
        }
    }

    private fun showLoading() {
        binding.apply {
            userListProgressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun showSuccess(users: List<User>) {
        binding.apply {
            swipeRefreshLayout.isRefreshing = false
            userListProgressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            adapter.submitList(users)
        }
    }

    private fun showError(message: String?) {
        binding.apply {
            userListProgressBar.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
            recyclerView.visibility = View.GONE
            Toast.makeText(
                root.context,
                message ?: getString(R.string.error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}