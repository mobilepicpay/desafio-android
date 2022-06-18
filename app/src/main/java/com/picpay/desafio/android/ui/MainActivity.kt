package com.picpay.desafio.android.ui

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.datasource.repository.UserRepository
import com.picpay.desafio.android.domain.repository.UserDataRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val repository: UserDataRepository by inject()

    override fun onResume() {
        super.onResume()
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val users = repository.getUsers()
                progressBar.visibility = View.GONE

                adapter.users = users
            }catch (exception:Exception){
                val message = getString(R.string.error)

                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE

                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
