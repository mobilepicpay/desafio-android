package com.picpay.desafio.android.extensions

val String.Companion.EMPTY: String get() = ""

fun String?.takeIfNotBlank() = takeIf { it?.isNotBlank().orFalse() }