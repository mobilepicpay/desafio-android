package com.picpay.desafio.android.ui.contact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.R

class ContactFragment : Fragment(R.layout.contact_fragment) {

    private lateinit var viewModel: ContactViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
    }
}