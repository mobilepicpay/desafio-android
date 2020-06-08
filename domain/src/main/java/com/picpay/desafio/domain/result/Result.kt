package com.picpay.desafio.domain.result

sealed class Result<out T : Any>

class OnSuccess<out T : Any>(val data: T) : Result<T>()

class OnError(
    val exception: Throwable
) : Result<Nothing>()

object OnLoading : Result<Nothing>()

object OnComplete : Result<Nothing>()