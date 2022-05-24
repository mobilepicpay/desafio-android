package com.picpay.desafio.android.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presenter.adapter.ContactAdapter
import com.picpay.desafio.android.presenter.adapter.ContactHeaderAdapter
import com.picpay.desafio.android.presenter.viewmodel.ContactState
import com.picpay.desafio.android.presenter.viewmodel.ContactViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<ContactViewModel>()

    private var binder: ActivityMainBinding? = null
    private lateinit var contactsAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater).also {
            contactsAdapter = ContactAdapter()
            it.contactList.apply {
                adapter = ConcatAdapter(ContactHeaderAdapter(), contactsAdapter)
            }
            it.buttonTryAgain.setOnClickListener { viewModel.loadContacts() }
        }
        setContentView(binder?.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { handleState(it) }
        }
    }

    private fun handleState(state: ContactState) = when (state) {
        ContactState.Error -> {
            binder?.contactList?.isVisible = false
            binder?.loading?.isVisible = false
            binder?.groupError?.isVisible = true
        }
        ContactState.Initial -> {
            binder?.contactList?.isVisible = false
            binder?.loading?.isVisible = false
            binder?.groupError?.isVisible = false
        }
        ContactState.Loading -> {
            binder?.contactList?.isVisible = false
            binder?.loading?.isVisible = true
            binder?.groupError?.isVisible = false
        }
        is ContactState.Success -> {
            contactsAdapter.submitList(state.data)
            binder?.contactList?.isVisible = true
            binder?.loading?.isVisible = false
            binder?.groupError?.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binder = null
    }
}
