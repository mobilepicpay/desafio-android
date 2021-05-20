package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.ui.adapter.UserAdapter
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.picpay.desafio.android.ui.viewmodel.state.UserViewModelState
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.item_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity(R.layout.activity_user) {

    private val userViewModel by viewModel<UserViewModel>()

    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        observeViewModelState()
        userViewModel.getAllUsers()
    }

    private fun observeViewModelState() {
        userViewModel.getState().observe(this) {
            when (it) {
                is UserViewModelState.Success -> handleSuccess(it.users)
                is UserViewModelState.Loading -> handleLoading()
                is UserViewModelState.Error -> handleError()
            }
        }
    }

    private fun handleSuccess(users: List<User>) {
        user_list_progress_bar.isVisible = false
        adapter.setUsers(users)
    }

    private fun handleLoading() {
        user_list_progress_bar.isVisible = true
    }

    private fun handleError() {
        user_list_progress_bar.isVisible = false
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT)
            .show()
    }
}
