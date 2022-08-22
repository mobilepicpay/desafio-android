package com.picpay.desafio.android.users.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.picpay.desafio.android.databinding.FragmentUsersBinding
import com.picpay.desafio.android.users.presentation.view.adapter.UserListAdapter
import com.picpay.desafio.android.users.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersViewModel by viewModels()

    private lateinit var usersAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@UsersFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
        viewModel.init()
    }

    private fun setupViews() = with(binding) {
        usersAdapter = UserListAdapter()
        recyclerView.adapter = usersAdapter
    }

    private fun observeViewState() = with(viewModel.uiState) {
        users.observe(viewLifecycleOwner) { userList ->
            usersAdapter.submitList(userList)
        }
    }
}