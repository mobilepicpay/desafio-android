package com.picpay.desafio.android.network

import android.util.Log

suspend fun <T> safeDataRequest(function: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(function())
    } catch (throwable: Throwable) {
        Log.e("SafeDataRequest", throwable.message, throwable)
        ResultWrapper.Error(throwable)
    }
}