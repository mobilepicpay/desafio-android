package com.picpay.desafio.android.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.picpay.desafio.android.extensions.viewModelClass
import org.koin.androidx.viewmodel.ext.android.getViewModel

abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    abstract val binding: ViewBinding
    abstract fun initComponents()
    abstract fun initObservers()
    val viewModel: V by lazy { getViewModel(clazz = viewModelClass()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initObservers()
    }
}