package com.picpay.desafio.android.ui.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.presentation.UserViewModel
import com.picpay.desafio.android.ui.presentation.UserViewObject
import com.picpay.desafio.android.ui.presentation.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var adapter: UserListAdapter

    private val viewModel: UserViewModel by viewModel()

    private var users: ArrayList<UserViewObject>? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        observeLoadUsers()
        viewModel.fetchUsers()
    }

    private fun observeLoadUsers() {
        viewModel.userState.observe(this, { state ->
            bindVisibility(state)
            when (state) {
                is ViewState.Success -> {
                    users = state.data
                    setupAdapter()
                }
                is ViewState.Empty -> {
                    // mostrar uma view vazia }
                }
                is ViewState.Error -> {
                    //mostrar uma view generica de error
                }
            }
        })
    }

    private fun setupAdapter(){
        adapter = UserListAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter.users = users as ArrayList<UserViewObject>
    }

    private fun bindVisibility(state: ViewState<ArrayList<UserViewObject>>) {
        binding.userListProgressBar.isVisible = state.progress
        binding.title.isVisible = state.success
        binding.rvUsers.isVisible = state.success
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val bundle = Bundle()
        bundle.putParcelableArrayList(LIST_USER, users)
        onSaveInstanceState(bundle)
    }

    companion object {
        private const val LIST_USER = "list_user"
    }
}