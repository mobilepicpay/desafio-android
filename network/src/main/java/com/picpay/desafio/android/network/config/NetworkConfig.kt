package com.picpay.desafio.android.network.config

import com.picpay.desafio.android.model.ErrorResponse
import com.picpay.desafio.android.network.util.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class NetworkConfig {

    suspend fun <T> callService(call: suspend () -> Response<T>): T {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                response.body() ?: throw ErrorResponse(
                    -1,
                    "response body null"
                )
            } catch (throwable: Throwable) {
                when (throwable) {
                    is NoConnectivityException,
                    is IOException ->
                        throw ErrorResponse(-1, "Not Connection")
                    is HttpException -> {
                        throw ErrorResponse(
                            throwable.code(),
                            throwable.message()
                        )
                    }
                    else -> {
                        throw ErrorResponse(
                            -1,
                            throwable.message ?: throwable.toString()
                        )
                    }
                }
            }
        }
    }
}