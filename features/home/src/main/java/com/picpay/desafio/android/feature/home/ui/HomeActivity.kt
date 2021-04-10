package com.picpay.desafio.android.feature.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.feature.home.R
import com.picpay.desafio.android.feature.home.ui.adapter.UserListAdapter
import com.picpay.desafio.android.feature.home.databinding.HomeActivityBinding
import com.picpay.desafio.android.shared.data.PicPayService
import com.picpay.desafio.android.shared.data.User
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter

    private val service: PicPayService by inject()

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        adapter = UserListAdapter()
        binding.recyclerView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
        binding.userListProgressBar.visibility = View.VISIBLE

        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val message = getString(R.string.error)

                    binding.userListProgressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE

                    Toast.makeText(this@HomeActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    binding.userListProgressBar.visibility = View.GONE

                    adapter.users = response.body()!!
                }
            })
    }
}