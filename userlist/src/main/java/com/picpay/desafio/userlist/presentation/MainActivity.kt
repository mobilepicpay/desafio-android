package com.picpay.desafio.userlist.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.common.components.CustomView
import com.picpay.desafio.userlist.R
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.presentation.adapter.UserListAdapter
import com.picpay.desafio.userlist.presentation.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var cvEmptyView: CustomView
    private lateinit var cvErrorView: CustomView

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        cvErrorView = findViewById(R.id.cv_error_view)
        cvEmptyView = findViewById(R.id.cv_empty_view)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        viewModel.userListResponse.observe(this, {
            handleResponse(it)
        })
    }

    private fun handleResponse(response: Resource<List<User>>) {
        when (response.status) {
            Resource.Status.SUCCESS -> {
                response.value?.let { showList(it) } ?: showEmptyView()
            }
            Resource.Status.ERROR -> showError()
            Resource.Status.LOADING -> showLoading()
        }
    }

    private fun showLoading() {
        cvErrorView.visibility = View.GONE
        cvEmptyView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        cvErrorView.apply {
            visibility = View.VISIBLE
            setOnTryAgain { viewModel.getList() }
        }
    }

    private fun showList(list: List<User>) {
        if (list.isEmpty()) {
            showEmptyView()
        } else {
            progressBar.visibility = View.GONE
            adapter.users = list
        }
    }

    private fun showEmptyView() {
        progressBar.visibility = View.GONE
        cvEmptyView.visibility = View.VISIBLE
    }
}
