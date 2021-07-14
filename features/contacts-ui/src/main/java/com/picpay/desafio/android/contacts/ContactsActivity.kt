package com.picpay.desafio.android.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.contact.R
import com.picpay.desafio.android.contact.databinding.ActivityContactsBinding
import com.picpay.desafio.android.contacts.adapter.UserListAdapter
import com.picpay.desafio.android.shared.extensions.showToast
import com.picpay.desafio.android.shared.extensions.toGone
import com.picpay.desafio.android.shared.extensions.toVisible
import com.picpay.desafio.android.shared.model.ViewUser
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Vitor Herrmann on 12/07/2021
 */
class ContactsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityContactsBinding.inflate(layoutInflater) }
    private val viewModel: ContactsViewModel by viewModel()
    private val userListAdapter: UserListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObservers()
        setupRecyclerView()

        binding.refresh.setOnRefreshListener { viewModel.updateContacts() }
        viewModel.fetchContacts()
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        adapter = userListAdapter
        layoutManager = LinearLayoutManager(this@ContactsActivity)
    }

    private fun setupObservers() = with(viewModel) {
        onFetchContactsSuccess.observe(this@ContactsActivity, { handleSuccess(it) })
        onError.observe(this@ContactsActivity, { handleError() })
        onShowLoading.observe(this@ContactsActivity, { showLoading() })
        onHideLoading.observe(this@ContactsActivity, { hideLoading() })
    }

    private fun hideLoading() = binding.apply {
        userListProgressBar.toGone()
        refresh.isRefreshing = false
    }

    private fun showLoading() = binding.userListProgressBar.toVisible()

    private fun handleError() {
        hideLoading()
        binding.recyclerView.toGone()
        showToast(getString(R.string.error))
    }

    private fun handleSuccess(contacts: List<ViewUser>) = userListAdapter.apply {
        users = contacts
        notifyDataSetChanged()
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ContactsActivity::class.java)
    }
}
