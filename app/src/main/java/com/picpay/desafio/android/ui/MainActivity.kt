package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.source.local.toUsersModel
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

        viewModel.users.observe(this, {
            val list = it.data.toUsersModel()
            rvAdapter.submitList(list)
        })

    }

}
