package com.picpay.desafio.presentation.features.user.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.picpay.desafio.presentation.base.BaseFragment
import com.picpay.desafio.presentation.features.user.detail.viewmodel.UserDetailViewModel
import com.picpay.desafio.domain.result.OnError
import com.picpay.desafio.domain.result.OnSuccess
import com.picpay.desafio.domain.result.Result
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.presentation.R
import com.picpay.desafio.presentation.utils.ImageHelper
import kotlinx.android.synthetic.main.user_detail_fragment.*
import org.koin.android.ext.android.inject

class UserDetailFragment : BaseFragment() {

    private val userDetailViewModel: UserDetailViewModel by inject()
    private val observer = Observer<Result<User>> { onResult(it) }

    private var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        userDetailViewModel.getUserLiveDate().observe(viewLifecycleOwner, observer)
        arguments?.let {
            userId = UserDetailFragmentArgs.fromBundle(it).userId
        }
        userDetailViewModel.getUser(userId)
        super.onActivityCreated(savedInstanceState)
    }

    private fun onResult(result: Result<User>) {
        when (result) {
            is OnSuccess<User> -> handleSuccess(result.data)
            is OnError -> handleError(result.exception)
        }
    }

    private fun handleSuccess(user: User) {
        detailName.text = user.name
        detailUsername.text = user.username
        ImageHelper.downloadImage(detailPicture, user.img, ::onImageDownloadComplete)
    }

    private fun onImageDownloadComplete() {
        progressBar.visibility = GONE
    }
}