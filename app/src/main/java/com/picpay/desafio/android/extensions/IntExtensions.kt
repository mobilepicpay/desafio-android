package com.picpay.desafio.android.extensions

val Int.Companion.ZERO: Int get() = 0

val Int.Companion.NULL: Int? get() = null

fun Int?.orZero() = this ?: Int.ZERO