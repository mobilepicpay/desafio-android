package com.picpay.desafio.android.ui.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.entities.UsersDomain
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var usersListAdapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()

        binding.userListProgressBar.visibility = View.VISIBLE

        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchUsers()

        viewModel.usersLiveData.observe(this, Observer { state ->
            state.getContentIfNotHandled()?.let {
                when (it) {
                    is MainViewModel.ViewUsersStates.Show -> showContent(it.list)
                    MainViewModel.ViewUsersStates.Empty -> showEmptyState()
                    MainViewModel.ViewUsersStates.Error -> showError()
                }
            }
        })
    }

    private fun showError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showEmptyState() {
        TODO("Not yet implemented")
    }

    private fun showContent(users: List<UsersDomain>) {
        binding.userListProgressBar.visibility = View.GONE
        usersListAdapter.users = users
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        usersListAdapter = UserListAdapter()

        layoutManager = LinearLayoutManager(context)
        adapter = usersListAdapter
    }
}
