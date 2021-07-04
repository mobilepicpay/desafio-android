package com.picpay.desafio.android.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.remote.RetrofitService
import com.picpay.desafio.android.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()
        initViewModel()
        configRecyclerView()
        callUsers()

    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        recyclerView = binding.recyclerView
        progressBar = binding.userListProgressBar
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(RetrofitService.getInstance())
        )
            .get(MainViewModel::class.java)
    }

    private fun configRecyclerView() {
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun callUsers() {
        progressBar.visibility = View.VISIBLE

        viewModel.getUsers()
        initObservers()
    }

    private fun initObservers() {
        viewModel.userList.observe(this, Observer {
            adapter.users = it
        })

        viewModel.progressBar.observe(this, Observer {
            progressBar.visibility = it
        })

        viewModel.recyclerView.observe(this, Observer {
            recyclerView.visibility = it
        })
    }

}
