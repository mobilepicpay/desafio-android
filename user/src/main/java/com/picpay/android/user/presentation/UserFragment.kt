package com.picpay.android.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.picpay.android.user.R
import com.picpay.android.user.databinding.UserFragmentBinding
import com.picpay.android.user.presentation.adapter.UserListAdapter
import com.picpay.android.util.BaseFragment
import com.picpay.android.network.ViewModelResponse
import com.picpay.android.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment(R.layout.user_fragment) {

    private val userViewModel: UserViewModel by viewModel()
    private val binding by viewBinding(UserFragmentBinding::bind)

    private val userAdapter by lazy {
        UserListAdapter().apply {
            userItemClick = {
                navigateTo(UserFragmentDirections.actionUserFragmentToUserDetailFragment(it))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycle.addObserver(userViewModel)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userList.adapter = userAdapter

        configureLiveData()

        val color = ContextCompat.getColor(requireContext(), R.color.colorTitleBar)

        binding.collToolbar.apply {
            title = context.getString(R.string.title)
            setExpandedTitleColor(color)
            setCollapsedTitleTextColor(color)
        }
    }

    private fun configureLiveData() {
        with(userViewModel) {
            userLiveData.observe(viewLifecycleOwner) { viewModelResponse ->
                when (viewModelResponse) {
                    is ViewModelResponse.Loading -> {
                        statusLoad(viewModelResponse.load)
                    }
                    is ViewModelResponse.Success -> {
                        userAdapter.refreshAdapter(viewModelResponse.value)
                    }
                    is ViewModelResponse.Error -> {
                        if (viewModelResponse.customError.errorMessageRes > 0) {
                            showWarningMessage(viewModelResponse.customError.errorMessageRes)
                        } else {
                            showWarningMessage(viewModelResponse.customError.errorMessage)
                        }
                    }
                }
            }
        }
    }
}