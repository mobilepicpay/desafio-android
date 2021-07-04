package com.picpay.desafio.android

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.commons.extensions.showToast
import com.picpay.desafio.android.commons.extensions.visible
import com.picpay.desafio.android.commons.util.NetworkUtil
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.state.UserState
import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModel()
    private var binding: ActivityMainBinding? = null
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupFields()
        setupObservers()
        getUsers()
    }

    private fun setupObservers() {
        userViewModel.state.observe(this) { state ->
            when (state) {
                is UserState.ShowLoading -> showLoading(state.show)
                is UserState.Error -> {
                    showToast(getString(R.string.error))
                    hideRecyclerView()
                }

                is UserState.ShowUserList -> {
                    adapter.users = state.userList
                }
            }
        }
    }

    private fun hideRecyclerView() {
        binding?.recyclerView?.visible(false)
    }

    private fun getUsers() {
        userViewModel.getUsers(NetworkUtil.isNetworkConnected(this@MainActivity))
    }

    private fun setupFields() {
        binding?.apply {
            setupRecycler()

//            .enqueue(object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    val message = getString(R.string.error)
//
//                    progressBar.visibility = View.GONE
//                    recyclerView.visibility = View.GONE
//
//                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    progressBar.visibility = View.GONE
//
//                    adapter.users = response.body()!!
//                }
//            })
        }
    }

    private fun showLoading(show: Boolean) {
        binding?.apply {
            userListProgressBar.visible(show)
        }
    }

    private fun setupRecycler() {
        binding?.apply {
            adapter = UserListAdapter()
            recyclerView.apply {
                adapter = adapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }
}
