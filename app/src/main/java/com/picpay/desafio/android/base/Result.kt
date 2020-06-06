package com.picpay.desafio.android.base

sealed class Result<out T : Any>

class OnSuccess<out T : Any>(val data: T) : Result<T>()

class OnError(
    val exception: Throwable
) : Result<Nothing>()

class OnProgress(val loading: Boolean) : Result<Nothing>()