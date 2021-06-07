package com.picpay.desafio.android.features

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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val usersListAdapter: UserListAdapter by inject()
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

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
                binding.userListProgressBar.visibility = View.GONE
            }
        })
    }

    private fun showError() {
        binding.recyclerView.visibility = View.GONE
        runOnUiThread {
            Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showEmptyState() {
        runOnUiThread {
            Toast.makeText(this@MainActivity, getString(R.string.listEmpty), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showContent(users: List<UsersDomain>) {

        usersListAdapter.users = users
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = usersListAdapter
    }
}
