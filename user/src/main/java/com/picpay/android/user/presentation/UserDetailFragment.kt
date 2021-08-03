package com.picpay.android.user.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.picpay.android.user.R
import com.picpay.android.user.databinding.FragmentUserDetailBinding
import com.picpay.android.util.BaseFragment
import com.picpay.android.util.viewBinding

class UserDetailFragment : BaseFragment(R.layout.fragment_user_detail) {

    private val binding by viewBinding(FragmentUserDetailBinding::bind)
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = args.user
        super.onViewCreated(view, savedInstanceState)
    }

}