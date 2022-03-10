package com.picpay.desafio.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.adapters.ContactAdapter
import com.picpay.desafio.android.R
import com.picpay.desafio.util.Resource
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment(R.layout.fragment_user_list){

    lateinit var viewModel: ContactViewModel
    lateinit var contactAdapter: ContactAdapter

    val TAG = "contactlistFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as UsersActivity).viewModel
        setupRecyclerView()

        viewModel.contacts.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {contactListResponse ->
                        contactAdapter.differ.submitList(contactListResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "an error ocured: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar() {
        progress_bar_user_list_fragment.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar_user_list_fragment.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        contactAdapter = ContactAdapter()
        recycler_view_user_list_fragment.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}