package com.picpay.desafio.android.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.repository.model.UserResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListActivity : AppCompatActivity(R.layout.activity_user_list) {

    private lateinit var recyclerView: RecyclerView
    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.user_list_progress_bar)
    }
    private val userListEmpty by lazy {
        findViewById<TextView>(R.id.user_list_empty)
    }
    private lateinit var adapter: UserListAdapter
    private val viewModel: UserListViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        configureRecyclerView()
        getUsers()
    }

    private fun getUsers() {
        tryLoadListaLocal()
        tryLoadListaApi()
    }

    private fun tryLoadListaLocal() {
        viewModel.getUser().observe(this, Observer {
            if (it.isNullOrEmpty()) {
                userListEmpty.visibility = View.VISIBLE
                userListEmpty.text = getString(R.string.listaVazia)
            } else {
                adapter.differ.submitList(it)
            }
        })
    }

    private fun tryLoadListaApi() {
        viewModel.liveData.observe(this, Observer {
            when (it) {
                is UserResponse.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
                is UserResponse.Loading -> progressBar.visibility = View.VISIBLE
                is UserResponse.Success -> progressBar.visibility = View.GONE
            }
        })
    }

    private fun configureRecyclerView() {
        recyclerView = findViewById(R.id.user_list_recyclerView)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
    }
}
