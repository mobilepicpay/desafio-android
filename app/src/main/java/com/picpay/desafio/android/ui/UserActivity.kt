package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        observeViewModelState()
        userViewModel.getAllUsers()
        recyclerView.adapter = adapter
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
        user_list_progress_bar.visibility = GONE
        adapter.setUsers(users)
    }

    private fun handleLoading() {
        user_list_progress_bar.visibility = VISIBLE
    }

    private fun handleError() {
        val message = "Erro ao carregar a lista"
        Toast.makeText(this@UserActivity, message, Toast.LENGTH_SHORT)
            .show()
    }
}
