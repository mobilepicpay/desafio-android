package com.picpay.desafio.android.feature.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.feature.home.R
import com.picpay.desafio.android.feature.home.databinding.HomeActivityBinding
import com.picpay.desafio.android.feature.home.interactor.user.UserEntity
import com.picpay.desafio.android.feature.home.ui.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter

    private lateinit var binding: HomeActivityBinding

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindView()
        setupView()

        setupObservables()
        viewModel.onCreate()
    }

    private fun bindView() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView() {
        adapter = UserListAdapter()
        binding.recyclerView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun setupObservables() {
        viewModel.state.observe(this, { it?.let { renderState(it) } })
        viewModel.event.observe(this, { it?.let { performEvent(it) } })
    }

    private fun renderState(state: HomeViewModel.HomeViewState) {
        Timber.i("State ----> ${state::class.java.simpleName}")
        when (state) {
            HomeViewModel.HomeViewState.Error -> renderError()
            HomeViewModel.HomeViewState.Loading -> renderLoading()
            is HomeViewModel.HomeViewState.UserList -> renderUserList(state.list)
        }
    }

    private fun renderError() {
        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }

    private fun renderLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun renderUserList(list: List<UserEntity>) {
        binding.userListProgressBar.visibility = View.GONE

        adapter.users = list
    }

    private fun performEvent(event: HomeViewModel.HomeViewEvent) {
        Timber.i("Event ----> ${event::class.java.simpleName}")
        when (event) {
            HomeViewModel.HomeViewEvent.SendErrorToast -> performSendErrorToast()
        }
    }

    private fun performSendErrorToast() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
    }
}