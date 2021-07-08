package com.picpay.desafio.android.domain

sealed class Result<T>{
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val error: UseCaseError) : Result<T>()
}