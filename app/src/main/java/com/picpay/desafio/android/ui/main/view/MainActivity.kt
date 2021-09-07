package com.picpay.desafio.android.ui.main.view

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.app.gone
import com.picpay.desafio.android.app.print
import com.picpay.desafio.android.app.visible
import com.picpay.desafio.android.ui.main.view.adapter.UserListAdapter
import com.picpay.desafio.android.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val vm: MainViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var userAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        userAdapter = UserListAdapter()

        setLoadingView()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setLoadingView(isLoading: Boolean = true) {
        recyclerView.gone()
        progressBar.isVisible = isLoading
    }

    private fun setupObservers() {
        Log.d("Viewmodel", vm.toString())

        vm.state.observe(this) {
            setLoadingView(it.isLoading)
            it.users?.let { users ->
                userAdapter.users = users
                setListViewState()
            }
            it.error?.let {
                setErrorState()
            }
        }
    }

    private fun setErrorState() {
        recyclerView.gone()
        showToastError(R.string.error.print())
    }

    private fun showToastError(error: String) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT)
            .show()
    }

    private fun setListViewState() {
        recyclerView.visible()
    }
}
