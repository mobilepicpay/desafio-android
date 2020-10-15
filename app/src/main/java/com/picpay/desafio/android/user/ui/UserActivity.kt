package com.picpay.desafio.android.user.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.viewmodel.UserViewModel
import com.picpay.desafio.android.user.viewmodel.state.UserState
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {
    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val viewModel: UserViewModel by viewModel()

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_users)
    }
    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.pb_user_list_progress_bar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initObservables()
        bindViews()
    }

    private fun initObservables() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is UserState.SuccessApi -> successApiUsers(it.users)
                is UserState.ErrorApi -> errorApiUsers(it.message)
            }
        })
    }

    private fun bindViews() {
        viewModel.getContacts()
    }

    private fun initViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
    }

    private fun successApiUsers(users: List<User>) {
        adapter.users = users
        toggleProgressbar(false)
        toggleList(true)
    }

    private fun errorApiUsers(message: String) {
        showMessageError(message)
        toggleProgressbar(false)
        toggleList(false)
    }

    private fun toggleList(show: Boolean) {
        recyclerView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun toggleProgressbar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showMessageError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
