package com.picpay.desafio.android.extensions

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

internal fun <T : Any> T.toJson() = Gson().toJson(this)

@Suppress("UNCHECKED_CAST")
internal fun <T> Any.toViewModelClass(): Class<T> {
    val type = javaClass.genericSuperclass as ParameterizedType
    return type.actualTypeArguments[0] as Class<T>
}