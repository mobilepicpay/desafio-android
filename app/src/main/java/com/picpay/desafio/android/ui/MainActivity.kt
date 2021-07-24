package com.picpay.desafio.android.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    val rvAdapter = UserListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = rvAdapter
        setActionsViewModel()
        setupObserverViewModel()

    }

    private fun setActionsViewModel() {
        viewModel.fetchUsers()
    }

    private fun setupObserverViewModel() {
        viewModel.users.observe(this, { resource ->
            when (resource) {
                is Resource.Error -> {
                    Log.d("ALEDEV","OPS ERROR")
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    rvAdapter.submitList(resource.data)
                }
            }
        })
    }

}
