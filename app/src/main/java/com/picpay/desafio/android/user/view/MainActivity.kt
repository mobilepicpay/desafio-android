package com.picpay.desafio.android.user.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.user.view.adapter.UserListAdapter
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.extension.viewBinding
import com.picpay.desafio.android.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val userAdapter: UserListAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupAdapter()
        setupObservers()
        viewModel.getUsers()
    }

    private fun setupObservers() {
        viewModel.state.observe(this) {
            binding.progressBar.isVisible = it.isLoading
            it.users?.let(userAdapter::updateUsers)
            it.error?.let(::showError)
        }
    }

    private fun setupAdapter() {
        binding.recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
