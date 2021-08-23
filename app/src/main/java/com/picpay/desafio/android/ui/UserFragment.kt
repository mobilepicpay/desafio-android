package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.databinding.FragmentUserBinding
import com.picpay.desafio.android.model.ErrorResponse
import com.picpay.desafio.android.util.errorView
import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserBinding.inflate(inflater).also {
           _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadList().observe(viewLifecycleOwner, {
            binding.uiListUsers.setData(it.data.orEmpty())

            if (it is com.picpay.desafio.android.util.Resource.Loading && it.data.isNullOrEmpty()) binding.uiListUsers.starLoading()
            if (it is com.picpay.desafio.android.util.Resource.Error && it.data.isNullOrEmpty()) {
                binding.uiListUsers.stopLoading()
                errorHandler(it.error)
            }
        })
    }

    private fun errorHandler(error: Throwable?) {
        val msgError = if (error is ErrorResponse) error.massage else error?.message
        errorView(binding.root, msgError.orEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}