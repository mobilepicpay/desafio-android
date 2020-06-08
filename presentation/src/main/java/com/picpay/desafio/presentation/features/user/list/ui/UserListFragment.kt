package com.picpay.desafio.presentation.features.user.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.presentation.base.BaseFragment
import com.picpay.desafio.presentation.features.user.list.adapter.UserListAdapter
import com.picpay.desafio.presentation.features.user.list.viewmodel.UserListViewModel
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.result.*
import com.picpay.desafio.presentation.R
import kotlinx.android.synthetic.main.user_list_fragment.*
import org.koin.android.ext.android.inject

class UserListFragment : BaseFragment() {

    private val userListViewModel: UserListViewModel by inject()
    private val userListAdapter: UserListAdapter by inject()
    private val observer = Observer<Result<List<User>>> { onResult(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        userListViewModel.getUsersLiveDate().observe(viewLifecycleOwner, observer)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
    }

    private fun onResult(result: Result<List<User>>) {
        when (result) {
            is OnSuccess<List<User>> -> handleSuccess(result.data)
            is OnError -> handleError(result.exception)
            is OnLoading -> handleProgress(true)
            is OnComplete -> handleProgress(false)
        }
    }

    private fun handleSuccess(userList: List<User>) {
        userListAdapter.setData(userList, ::onItemListClick)
    }

    private fun onItemListClick(userId: Int) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment()
        action.userId = userId
        view?.findNavController()?.navigate(action)
    }

    private fun handleProgress(loading: Boolean) {
        progressBar.visibility = toggleVisibility(loading)
    }
}