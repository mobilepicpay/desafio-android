package com.picpay.desafio.userlist.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.R
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.presentation.adapter.UserListAdapter
import com.picpay.desafio.userlist.presentation.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun setupView(){
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers(){
        viewModel.userListResponse.observe(this, {
            handleResponse(it)
        })
    }

    private fun handleResponse(response:Resource<List<User>>){
        when(response.status){
            Resource.Status.SUCCESS -> {
                response.value?.let { showList(it) } ?: showEmptyView()
            }
            Resource.Status.ERROR -> showError()
            Resource.Status.LOADING -> progressBar.visibility = View.VISIBLE
        }
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        Snackbar.make(window.decorView.rootView,"Ocorreu um erro", Snackbar.LENGTH_SHORT).show()
    }

    private fun showList(list:List<User>){
        progressBar.visibility = View.GONE
        adapter.users = list
    }

    private fun showEmptyView(){
        //TODO: create empty view
    }
}
