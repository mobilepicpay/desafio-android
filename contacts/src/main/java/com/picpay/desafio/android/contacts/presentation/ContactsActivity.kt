package com.picpay.desafio.android.contacts.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.picpay.desafio.android.commons.base.BaseActivity
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.databinding.ActivityContactsBinding
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.picpay.desafio.android.contacts.presentation.adapter.ContactsListAdapter
import com.picpay.desafio.android.contacts.presentation.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : BaseActivity() {
    override val viewModel by viewModel<ContactsViewModel>()

    private lateinit var binding: ActivityContactsBinding
    private val adapter by lazy { ContactsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ContactsViewState.ShowError -> showErrorState()
                is ContactsViewState.ToggleLoading -> toggleLoading(state.isLoading)
                is ContactsViewState.ShowContacts -> showContactsList(state.contacts)
            }
        }
    }

    private fun showErrorState() {
        val message = getString(R.string.error)
        binding.userListProgressBar.isVisible = false
        binding.recyclerView.isVisible = false
        Toast
            .makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun toggleLoading(isLoading: Boolean) {
        binding.userListProgressBar.isVisible = isLoading
    }

    private fun showContactsList(list: List<Contact>) {
        adapter.contacts = list
    }
}