package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.user.UserListAdapter
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.viewmodel.UserViewModel
import com.picpay.desafio.android.user.viewmodel.events.UserViewEvents
import com.picpay.desafio.android.user.viewmodel.status.UserStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.user_list_progress_bar) }
    private lateinit var adapter: UserListAdapter
    private val viewmodel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeStates()
        observeEvents()
        viewmodel.start()
    }

    private fun observeStates() {
        viewmodel.state.observe(this, Observer { state ->
            state?.let {
                when (it) {
                    is UserStatus.UserError -> showError()
                    is UserStatus.UserSuccess -> showUserList(it.users)
                }
            }
        })
    }

    private fun observeEvents() {
        viewmodel.event.observe(this, Observer { event ->
            event?.let {
                when (it) {
                    is UserViewEvents.UserViewShowLoading -> progressBar.visibility = (it.visible)
                }
            }
        })
    }

    private fun showUserList(users: List<User>) {
        progressBar.visibility = View.GONE
        adapter.users = users
    }

    private fun showError() {
        val message = getString(R.string.error)
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onResume() {
        super.onResume()
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
    }
}