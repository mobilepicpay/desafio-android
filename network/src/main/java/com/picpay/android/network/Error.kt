package com.picpay.android.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Error(
    val code: String = "",
    val errorMessage: String = "",
    val errorType: ErrorType = ErrorType.DEFAULT,
    val callback: (() -> Unit)? = null
) : Exception(errorMessage) {

    companion object {
        fun parseException(errorType: Exception): Error {

            return when (errorType) {
                is SocketTimeoutException -> {
                    Error(
                        errorType = ErrorType.TIMEOUT,
                        errorMessage = errorType.message ?: ""
                    )
                }
                is UnknownHostException -> {
                    Error(
                        errorType = ErrorType.UNKNOWN_HOST,
                        errorMessage = errorType.message ?: ""
                    )
                }
                is InterruptedIOException -> {
                    Error(
                        errorType = ErrorType.INTERRUPTED_IO,
                        errorMessage = errorType.message ?: ""
                    )
                }
                else -> Error(
                    errorType = ErrorType.UNKNOWN,
                    errorMessage = errorType.message ?: ""
                )
            }
        }

        fun parseErrorBody(rawError: String?, errorCode: Int): Error {
            return if (rawError.isNullOrEmpty())
                Error(
                    code = errorCode.toString(),
                    errorType = ErrorType.UNKNOWN_SERVER,
                    errorMessage = rawError ?: ""
                )
            else getErrorModelJson(rawError)
        }

        private fun getErrorModelJson(errorJson: String): Error {
            val gson = GsonBuilder().create()
            return try {
                gson.fromJson<List<Error>>(errorJson).first()
            } catch (e: Exception) {
                gson.fromJson(errorJson, Error::class.java)
            }
        }

        private inline fun <reified T> Gson.fromJson(json: String): T =
            fromJson(json, object : TypeToken<T>() {}.type)
    }

    enum class ErrorType {
        TIMEOUT,
        INTERRUPTED_IO,
        UNKNOWN_HOST,
        UNKNOWN_SERVER,
        UNKNOWN,
        DEFAULT
    }
}