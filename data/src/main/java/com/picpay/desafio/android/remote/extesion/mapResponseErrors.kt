package com.picpay.desafio.android.remote.extesion

import com.picpay.desafio.android.responses.ResultRemote
import retrofit2.HttpException

fun Throwable.mapRemoteErrors(): ResultRemote.ErrorResponse {
    return when (this) {
        is HttpException -> {
            when (code()) {
                401, 403 -> ResultRemote.ErrorResponse.TokenExpired(this)
                else -> ResultRemote.ErrorResponse.Unknown(this)
            }
        }
        else -> ResultRemote.ErrorResponse.Unknown(this)
    }
}