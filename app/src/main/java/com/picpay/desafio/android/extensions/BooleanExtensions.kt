package com.picpay.desafio.android.extensions

val Boolean.Companion.FALSE: Boolean get() = false

fun Boolean?.orFalse() = this ?: Boolean.FALSE