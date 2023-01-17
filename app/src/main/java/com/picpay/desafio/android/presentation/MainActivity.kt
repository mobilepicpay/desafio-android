package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    override fun onResume() {
        super.onResume()

        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun onError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun onSuccess(list: List<User>) {
        binding.userListProgressBar.visibility = View.GONE
        adapter.submitList(list)
    }
}
