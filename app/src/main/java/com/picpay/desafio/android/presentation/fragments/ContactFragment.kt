package com.picpay.desafio.android.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.adapters.UserListAdapter
import com.picpay.desafio.android.presentation.viewModels.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactFragment : Fragment(R.layout.frag_contact) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val listAdapter: UserListAdapter by lazy { UserListAdapter() }
    private val contactViewModel: ContactViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.user_list_progress_bar)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        initObservers()
    }

    private fun initObservers() {
        with(contactViewModel) {
            isLoading.observe(viewLifecycleOwner) { progressBar.isVisible = it }
            messages.observe(viewLifecycleOwner) {
                recyclerView.visibility = View.GONE
                Toast.makeText(this@ContactFragment.context, getString(it), Toast.LENGTH_SHORT)
                    .show()
            }
            contacts.observe(viewLifecycleOwner) {
                recyclerView.visibility = View.VISIBLE
                listAdapter.submitList(it)
            }
            loadContacts()
        }
    }

}