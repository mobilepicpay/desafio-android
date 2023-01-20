package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserListAdapter

    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UserViewState.Success -> onSuccess(state.list)
                is UserViewState.Loading -> onLoading()
                is UserViewState.Error -> onError(state.error)
            }
        }
        viewModel.getUsers()
    }

    override fun onResume() {
        super.onResume()
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.btRefresh.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun onLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
        binding.btRefresh.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }

    private fun onError(error: DataError) {
        val
        message = if (error.statusCode != 0) {
            error.statusMessage ?: getString(R.string.error)
        } else {
            getString(R.string.error) + error.statusMessage
        }
        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.btRefresh.visibility = View.VISIBLE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun onSuccess(list: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE
        binding.userListProgressBar.visibility = View.GONE
        binding.btRefresh.visibility = View.GONE
        adapter.submitList(list)
    }
}
