package com.picpay.desafio.android.contacts.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.picpay.desafio.android.commons.base.BaseActivity
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.picpay.desafio.android.contacts.presentation.adapter.ContactsListAdapter
import com.picpay.desafio.android.contacts.presentation.viewmodel.ContactsViewModel
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : BaseActivity() {
    override val viewModel by viewModel<ContactsViewModel>()

    private val adapter by lazy { ContactsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(this) { state ->
            progressBar.isVisible = state.isLoading
            if (state.hasError) {
                showErrorState()
            } else {
                if (!state.isLoading) {
                    showContactsList(state.contacts)
                }
            }
        }
    }

    private fun showErrorState() {
        val message = getString(R.string.error)
        progressBar.isVisible = false
        recyclerView.isVisible = false
        Toast
            .makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showContactsList(list: List<Contact>) {
        progressBar.isVisible = false
        adapter.submitList(list)
    }
}