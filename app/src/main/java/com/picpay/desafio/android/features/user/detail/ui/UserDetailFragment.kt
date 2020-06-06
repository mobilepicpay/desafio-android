package com.picpay.desafio.android.features.user.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.base.OnError
import com.picpay.desafio.android.base.OnSuccess
import com.picpay.desafio.android.custom.aliases.UserResult
import com.picpay.desafio.android.features.user.detail.viewmodel.UserDetailViewModel
import com.picpay.desafio.android.room.models.User
import com.picpay.desafio.android.utils.ImageHelper
import kotlinx.android.synthetic.main.user_detail_fragment.*
import org.koin.android.ext.android.inject

class UserDetailFragment : BaseFragment() {

    private val userDetailViewModel: UserDetailViewModel by inject()
    private val observer = Observer<UserResult> { onResult(it) }

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

    private fun onResult(result: UserResult) {
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