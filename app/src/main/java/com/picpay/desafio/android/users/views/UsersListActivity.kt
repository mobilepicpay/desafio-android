package com.picpay.desafio.android.users.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.R
import com.picpay.desafio.android.common.LoadState
import com.picpay.desafio.android.common.LoadState.SUCCESS
import com.picpay.desafio.android.users.UserListAdapter
import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.repo.UsersApi
import com.picpay.desafio.android.users.viewmodels.UsersListViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val usersListViewModel: UsersListViewModel by viewModels()

    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: UsersApi by lazy {
        retrofit.create(UsersApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        progressBar.visibility = View.VISIBLE

        registerObservers()

        usersListViewModel.loadUsers(service)
    }

    private fun registerObservers() {
        usersListViewModel.usersListState.observe(this, Observer {
            when (it) {
                LoadState.ERROR -> {
                    handleError()
                }

                is SUCCESS -> {
                    populateUsers(it.data as List<UserResponse>)
                }
            }
        })
    }

    private fun handleError() {
        val message = getString(R.string.error)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun populateUsers(users: List<UserResponse>) {
        progressBar.visibility = View.GONE
        adapter.users = users
    }
}
