package com.picpay.android.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CustomError(
    val code: String = "",
    val errorMessage: String = "",
    val errorMessageRes: Int = 0,
    val errorType: ErrorType = ErrorType.DEFAULT,
    val callback: (() -> Unit)? = null
) : Exception(errorMessage) {



    companion object {
        fun parseException(errorType: Exception): CustomError {

            return when (errorType) {
                is SocketTimeoutException -> {
                    CustomError(
                        errorType = ErrorType.TIMEOUT,
                        errorMessage = errorType.message ?: ""
                    )
                }
                is UnknownHostException -> {
                    CustomError(
                        errorType = ErrorType.UNKNOWN_HOST,
                        errorMessage = errorType.message ?: ""
                    )
                }
                is InterruptedIOException -> {
                    CustomError(
                        errorType = ErrorType.INTERRUPTED_IO,
                        errorMessage = errorType.message ?: ""
                    )
                }
                else -> CustomError(
                    errorType = ErrorType.UNKNOWN,
                    errorMessage = errorType.message ?: ""
                )
            }
        }

        fun parseErrorBody(rawError: String?, errorCode: Int): CustomError {
            return if (rawError.isNullOrEmpty())
                CustomError(
                    code = errorCode.toString(),
                    errorType = ErrorType.UNKNOWN_SERVER,
                    errorMessage = rawError ?: ""
                )
            else getErrorModelJson(rawError)
        }

        private fun getErrorModelJson(errorJson: String): CustomError {
            val gson = GsonBuilder().create()
            return try {
                gson.fromJson<List<CustomError>>(errorJson).first()
            } catch (e: Exception) {
                gson.fromJson(errorJson, CustomError::class.java)
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