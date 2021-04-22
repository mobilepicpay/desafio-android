package com.picpay.desafio.android.networking

import com.picpay.desafio.android.domain.errors.ErrorConvert
import com.picpay.desafio.android.domain.errors.RemoteServiceError
import retrofit2.HttpException

object HttpErrorConvert : ErrorConvert {

    override suspend fun convert(incoming: Throwable) =
        when (incoming) {
            is HttpException -> translateUsingStatusCode(incoming.code())
            else -> incoming
        }

    private fun translateUsingStatusCode(code: Int) =
        when (code) {
            in 400..499 -> RemoteServiceError.ClientOrigin
            else -> RemoteServiceError.RemoteSystem
        }
}