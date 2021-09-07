package com.picpay.desafio.android

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.app.gone
import com.picpay.desafio.android.app.print
import com.picpay.desafio.android.app.visible
import com.picpay.desafio.android.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val vm: MainViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        adapter = UserListAdapter()

        setupLoadingView()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupLoadingView() {
        progressBar.visible()
    }

    private fun setupObservers() {
        Log.d("Viewmodel", vm.toString())
        vm.users.observe(this) { users ->
            adapter.users = users
            setListViewState()
        }

        vm.getUserError.observe(this) { error ->
            if (!error.isNullOrBlank()) {
                setErrorState()
            }
        }
    }

    private fun setErrorState() {
        progressBar.gone()
        recyclerView.gone()
        Toast.makeText(this@MainActivity, R.string.error.print(), Toast.LENGTH_SHORT)
            .show()
    }

    private fun setListViewState() {
        progressBar.gone()
        recyclerView.visible()
    }
}
