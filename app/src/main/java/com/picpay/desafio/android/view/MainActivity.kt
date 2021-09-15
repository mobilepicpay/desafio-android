package com.picpay.desafio.android.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.interactor.PicPayState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val adapter = UserListAdapter()

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureViews()

        observeGetUsersState()

        getUsers()
    }

    private fun configureViews() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        recyclerView.adapter = adapter
    }

    private fun observeGetUsersState() {
        mainViewModel.getUsersState.observe(this, Observer { state ->
            when (state) {
                is PicPayState.GetUsers.Empty -> {
                    adapter.users = emptyList()
                }
                is PicPayState.GetUsers.Data -> {
                    adapter.users = state.users
                }
                is PicPayState.GetUsers.Error -> {
                    hideRecyclerView()
                    showError()
                }
            }
            hideProgress()
        })
    }

    private fun getUsers() {
        showProgress()

        mainViewModel.getUsers()
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun hideRecyclerView() {
        recyclerView.visibility = View.GONE
    }

    private fun showError() {
        val message = getString(R.string.error)
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }
}
