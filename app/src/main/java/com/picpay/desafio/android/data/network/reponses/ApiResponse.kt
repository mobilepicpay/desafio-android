package com.picpay.desafio.android.data.network.reponses

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(
                error.message ?: "Erro desconhecido",
                500
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val headers = response.headers()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body,
                        headers
                    )
                }
            } else {
                val msg: String? = response.errorBody()?.string()
                val errorMsg = if (msg == null || msg == "") {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(
                    errorMsg ?: "Erro desconhecido",
                    response.code()
                )
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T?,
    val headers: okhttp3.Headers
) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String, val statusCode: Int) : ApiResponse<T>()