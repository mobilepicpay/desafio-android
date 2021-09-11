package com.picpay.desafio.android.domain.extensions

import com.picpay.desafio.android.network.R
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Throwable.handleErrorApiMessage(): Int {
    return when (this) {
        is UnknownHostException -> R.string.network_error
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> R.string.unavailable_error
                HttpURLConnection.HTTP_FORBIDDEN -> R.string.forbidden_error
                HttpURLConnection.HTTP_UNAVAILABLE -> R.string.unavailable_error
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> R.string.timeout_error
                else -> R.string.network_error
            }
        }
        else -> R.string.network_error
    }
}

