package com.picpay.desafio.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.R

class FavoriteUserFragment : Fragment(R.layout.fragment_favorite_user) {

    lateinit var viewModel: ContactViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as UsersActivity).viewModel
    }
}