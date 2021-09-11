package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presetation.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()
    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewBinding()
        setupAdapter()
        setupObservers()
        userViewModel.getUsers()
    }

    private fun setupObservers() {
        userViewModel.users.observe(this, { listUsers ->
            viewBinding.userListProgressBar.isVisible = listUsers.isLoading
            listUsers.error?.let { error -> setupError(error) }
            adapter.submitList(listUsers.listUsers)
        })
    }

    private fun setupError(error: Int) {
        Snackbar.make(viewBinding.root, getString(error), Snackbar.LENGTH_LONG).show()
    }

    private fun setupViewBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    private fun setupAdapter() {
        adapter = UserListAdapter()
        viewBinding.recyclerView.adapter = adapter
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
