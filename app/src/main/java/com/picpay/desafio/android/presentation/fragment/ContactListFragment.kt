package com.picpay.desafio.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseListFragment
import com.picpay.desafio.android.core.State
import com.picpay.desafio.android.databinding.FragmentUserListBinding
import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.listItemUser
import com.picpay.desafio.android.presentation.viewModel.ContactListViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.koin.core.component.inject


class ContactListFragment : BaseListFragment() {

    private val vm by inject<ContactListViewModel>()
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        vm.loadContacts()
    }

    override fun initObservers() {
        vm.responseState.observe(viewLifecycleOwner) { responseState ->
            when (responseState) {
                is State.Loading -> {
                    binding.userListProgressBar.visibility = View.VISIBLE
                }
                is State.Success -> {
                    binding.userListProgressBar.visibility = View.GONE

                    modelBuilder(responseState.result)
                }
                is State.Error -> {
                    binding.userListProgressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "" + responseState.error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun <T> modelBuilder(data: List<T>) {
        data as List<ContactModel>

        binding.recyclerView.withModels {
            data.forEachIndexed { _, model ->
                listItemUser {
                    id(model.id)
                    username(model.username)
                    name(model.name)

                    onBind { _, view, _ ->
                        val picture =
                            view.dataBinding.root.findViewById<CircleImageView>(R.id.picture)
                        val progressBar =
                            view.dataBinding.root.findViewById<ProgressBar>(R.id.progressBar)

                        Picasso.get()
                            .load(model.img)
                            .error(R.drawable.ic_round_account_circle)
                            .into(picture, object : Callback {
                                override fun onSuccess() {
                                    progressBar.visibility = View.GONE
                                }

                                override fun onError(e: Exception?) {
                                    progressBar.visibility = View.GONE
                                }
                            })
                    }

                    onUnbind { _, view ->
                        Picasso.get().invalidate(model.img)
                    }
                }
            }
        }
    }
}