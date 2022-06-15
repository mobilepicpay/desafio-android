package com.picpay.desafio.android.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.viewModel = viewModel
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
                    goneImageError()
                    state.data?.let { setAdapter(it) }
                }
                is Resource.Error<*> -> {
                    setImageError()
                }
                is Resource.Loading<*> -> {
                    binding.userListProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setImageError(){
        binding.run {
            userListProgressBar.visibility = View.GONE
            title.visibility = View.GONE
            constraintError.visibility = View.VISIBLE
        }
    }

    private fun goneImageError(){
        binding.run {
            userListProgressBar.visibility = View.GONE
            title.visibility = View.VISIBLE
            constraintError.visibility = View.GONE
        }
    }

    private fun setAdapter(data: List<User>) {
        adapter = UserListAdapter(data)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}
