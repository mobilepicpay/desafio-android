package com.picpay.desafio.android.extensions

import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
internal fun <T> Any.toViewModelClass(): Class<T> {
    val type = javaClass.genericSuperclass as ParameterizedType
    return type.actualTypeArguments[0] as Class<T>
}