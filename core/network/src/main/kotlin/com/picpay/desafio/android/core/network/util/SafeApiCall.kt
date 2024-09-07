package com.picpay.desafio.android.core.network.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): com.picpay.desafio.android.core.model.ApiResponse<T> {
    return withContext(dispatcher) {
        runCatching { apiCall() }
            .fold(
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            com.picpay.desafio.android.core.model.ApiResponse.Success(it)
                        } ?: com.picpay.desafio.android.core.model.ApiResponse.Error(response.code(), Throwable("Response body is null"))
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: response.message()
                        com.picpay.desafio.android.core.model.ApiResponse.Error(response.code(), Throwable(errorMessage))
                    }
                },
                onFailure = { exception ->
                    com.picpay.desafio.android.core.model.ApiResponse.Error(exception.hashCode(), exception)
                }
            )
    }
}