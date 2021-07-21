package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.adapter = userListAdapter
        inscribeObserver()
        viewModel.getUserList()
    }

    private fun inscribeObserver() {
        viewModel.loadingProgress.observe(this, Observer {
            binding.userListProgressBar.visibility = View.VISIBLE
        })
        viewModel.toastError.observe(this, Observer {
            binding.userListProgressBar.visibility = View.GONE
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        })
        viewModel.userList.observe(this, Observer {
            binding.userListProgressBar.visibility = View.GONE
            userListAdapter.users = it
        })
    }

}
