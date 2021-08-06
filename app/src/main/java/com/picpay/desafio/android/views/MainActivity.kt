package com.picpay.desafio.android.views

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.network.reponses.StatusResponse
import com.picpay.desafio.android.views.adapters.UserListAdapter
import com.picpay.desafio.android.utils.ComponentUtils
import com.picpay.desafio.android.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onResume() {
        super.onResume()
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        initRecyclerView()
        initViewModelObserver()
    }

    private fun initRecyclerView(){
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModelObserver(){
        with(viewModel) {
            users.observe(this@MainActivity, Observer { resource ->
                when (resource.status) {
                    StatusResponse.SUCCESS -> {
                        resource.data?.let { users ->
                            adapter.users = users
                            progressBarVisibilityHandler(false)
                            recyclerViewVisibilityHandler(true)
                        } ?: run {
                            showError()
                        }
                    }
                    StatusResponse.LOADING -> {
                        progressBarVisibilityHandler(true)
                    }
                    StatusResponse.FAILURE -> {
                        showError()
                    }
                    else -> {
                        showError()
                    }
                }
            })
        }
    }

    private fun showError(){
        ComponentUtils.showToast(this@MainActivity, getString(R.string.error))
        progressBarVisibilityHandler(false)
        recyclerViewVisibilityHandler(false)
    }

    private fun progressBarVisibilityHandler(show: Boolean){
        progressBar.visibility = if (show) View.VISIBLE else  View.GONE
    }

    private fun recyclerViewVisibilityHandler(show: Boolean){
        recyclerView.visibility = if (show) View.VISIBLE else  View.GONE
    }
}
