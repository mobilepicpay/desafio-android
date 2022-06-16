package com.picpay.desafio.android.extensions

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(layoutInflater) }

internal fun <V : Any> Fragment.getViewModelClass() = toViewModelClass<V>().kotlin