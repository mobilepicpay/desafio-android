package com.picpay.desafio.android.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.core.Resource
import com.picpay.desafio.android.data.response.User
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.view.adapter.UserListAdapter
import com.picpay.desafio.android.view.viewModel.ListUsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListUsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        initObservers()
    }

    private fun initObservers() {
        viewModel.allUsers.observe(this) { state ->
            when (state) {
                is Resource.Success<*> -> {
                    binding.userListProgressBar.visibility = View.GONE
                    state.data?.let { setAdapter(it) }
                }
                is Resource.Error<*> -> {
                    binding.userListProgressBar.visibility = View.GONE
                    this.let {
                        Snackbar.make(
                            binding.root,
                            state.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Loading<*> -> {
                    binding.userListProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setAdapter(data: List<User>) {
        adapter = UserListAdapter(data)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}
