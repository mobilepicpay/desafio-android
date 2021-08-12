package com.picpay.desafio.android.modules.main

import android.os.Bundle
import androidx.activity.viewModels
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.modules.base.BaseActivity
import com.picpay.desafio.android.uicomponents.adapters.UserAdapter
import com.picpay.desafio.android.utils.extensions.setupObserver
import com.picpay.desafio.android.utils.pokos.ErrorMessageViewObject
import com.picpay.desafio.android.utils.pokos.UserViewItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        setupRecyclerView()

        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshUsers()
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        setupObserver(viewModel.userViewItemsLiveData to ::userViewItemsObserver)
        setupObserver(viewModel.showLoadingLiveData to ::showLoadingObserver)
        setupObserver(viewModel.showSwipeToRefreshLoadingLiveData to ::showSwipeToRefreshLoadingObserver)
        setupObserver(viewModel.requestErrorLiveData to ::requestErrorObserver)
    }

    private fun userViewItemsObserver(userViewItems: List<UserViewItem>) {
        adapter.userViewItems = userViewItems
    }

    private fun showLoadingObserver(isLoading: Boolean) {
        showLoading(isLoading, binding.loadingImageView)
    }

    private fun showSwipeToRefreshLoadingObserver(isLoading: Boolean) {
        binding.swipeToRefreshLayout.isRefreshing = isLoading
    }

    private fun requestErrorObserver(errorMessageViewObject: ErrorMessageViewObject) {
        showShortErrorMessage(
            errorMessageViewObject,
            binding.root
        ) {
            errorMessageViewObject.actionResId?.let { viewModel.onRetryGetUsers() }
        }
    }
}
