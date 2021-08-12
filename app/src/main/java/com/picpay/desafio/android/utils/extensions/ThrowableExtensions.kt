package com.picpay.desafio.android.utils.extensions

import android.database.sqlite.SQLiteException
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Throwable.handleApiException(): DataErrorException.ApiErrorException {
    return when (this) {
        is UnknownHostException -> DataErrorException.ApiErrorException.NetworkConnectionException
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> DataErrorException.ApiErrorException.NotFoundException
                HttpURLConnection.HTTP_FORBIDDEN -> DataErrorException.ApiErrorException.ForbiddenException
                HttpURLConnection.HTTP_UNAVAILABLE -> DataErrorException.ApiErrorException.ServiceUnavailableException
                else -> DataErrorException.ApiErrorException.UnknownException
            }
        }
        else -> DataErrorException.ApiErrorException.UnknownException
    }
}

fun Throwable.handleDatabaseException(): DataErrorException.DatabaseErrorException {
    return when (this) {
        is SQLiteException -> DataErrorException.DatabaseErrorException.SqliteException
        else -> DataErrorException.DatabaseErrorException.UnknownException
    }
}
