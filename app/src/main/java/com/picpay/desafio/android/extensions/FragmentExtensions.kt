package com.picpay.desafio.android.extensions

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(layoutInflater) }

@Suppress("UNCHECKED_CAST")
internal fun <V : Any> Fragment.viewModelClass(): KClass<V> {
    val type = javaClass.genericSuperclass as ParameterizedType
    val result = type.actualTypeArguments[0] as Class<V>
    return result.kotlin
}