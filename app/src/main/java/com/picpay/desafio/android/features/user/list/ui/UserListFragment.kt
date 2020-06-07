package com.picpay.desafio.android.features.user.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.base.OnError
import com.picpay.desafio.android.base.OnProgress
import com.picpay.desafio.android.base.OnSuccess
import com.picpay.desafio.android.custom.aliases.ListOfUsers
import com.picpay.desafio.android.custom.aliases.UsersResult
import com.picpay.desafio.android.features.user.list.adapter.UserListAdapter
import com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.user_list_fragment.*
import org.koin.android.ext.android.inject

class UserListFragment : BaseFragment() {

    private val userListViewModel: UserListViewModel by inject()
    private val userListAdapter: UserListAdapter by inject()
    private val observer = Observer<UsersResult> { onResult(it) }

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

    private fun onResult(result: UsersResult) {
        when (result) {
            is OnSuccess<ListOfUsers> -> handleSuccess(result.data)
            is OnError -> handleError(result.exception)
            is OnProgress -> handleProgress(result.loading)
        }
    }

    private fun handleSuccess(userList: ListOfUsers) {
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