package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.data.utils.WebServiceFactory
import com.picpay.desafio.android.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadViewModel()
        loadViewBinding()
    }


    private fun loadViewModel() {
        val model: UserViewModel by viewModels()
        model.getUsers().observe(this, { users ->

        })
    }

    private fun loadViewBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        recyclerView = viewBinding.recyclerView
        progressBar = viewBinding.userListProgressBar

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        WebServiceFactory.createWebService<UserService>(okHttp,  BuildConfig.BASE_URL_API)
            .getUsers()
            .enqueue(object : Callback<List<UserResponse>> {
                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    val message = getString(R.string.error)

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    progressBar.visibility = View.GONE

                    adapter.submitList(response.body()!!)
                }
            })
    }
}
