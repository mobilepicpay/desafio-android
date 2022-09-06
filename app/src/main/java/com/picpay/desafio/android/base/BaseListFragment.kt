package com.picpay.desafio.android.base

import androidx.fragment.app.Fragment
import org.koin.core.component.KoinComponent

abstract class BaseListFragment : Fragment(), KoinComponent {
    abstract fun initObservers()
    abstract fun <T> modelBuilder(data: List<T>)
}