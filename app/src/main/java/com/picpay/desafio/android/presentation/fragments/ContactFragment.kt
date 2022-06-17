package com.picpay.desafio.android.presentation.fragments

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.bases.BaseFragment
import com.picpay.desafio.android.databinding.FragContactBinding
import com.picpay.desafio.android.extensions.*
import com.picpay.desafio.android.presentation.adapters.ContactListAdapter
import com.picpay.desafio.android.presentation.viewModels.ContactViewModel

class ContactFragment : BaseFragment<ContactViewModel>() {

    override val binding by viewBinding(FragContactBinding::inflate)
    private val listAdapter: ContactListAdapter by lazy { ContactListAdapter() }

    override fun initComponents() {
        with(binding) {
            srlContent.setTheme()
            srlContent.setOnRefreshListener(viewModel::loadContacts)
            recyclerView.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun initObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) { binding.srlContent.updateRefreshing(it.orFalse()) }
            messageResource.observe(viewLifecycleOwner) { resource ->
                binding.recyclerView.visibility = View.GONE
                resource?.let { this@ContactFragment.context?.showToastShortText(getString(it)) }
            }
            contacts.observe(viewLifecycleOwner) {
                binding.recyclerView.visibility = View.VISIBLE
                listAdapter.submitList(it)
            }
            loadContacts()
        }
    }

}