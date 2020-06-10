package com.picpay.desafio.android.features.user.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.features.user.detail.viewmodel.UserDetailViewModel
import com.picpay.desafio.android.features.user.list.ui.UserListFragmentDirections
import com.picpay.desafio.android.utils.ImageHelper
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.result.OnError
import com.picpay.desafio.domain.result.OnSuccess
import com.picpay.desafio.domain.result.Result
import kotlinx.android.synthetic.main.user_detail_fragment.*
import org.koin.android.ext.android.inject

class UserDetailFragment : BaseFragment() {

    private val args: UserDetailFragmentArgs by navArgs()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        userId = args.userId
        detailPicture.transitionName = args.userImg
        userDetailViewModel.getUserLiveDate().observe(viewLifecycleOwner, observer)
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