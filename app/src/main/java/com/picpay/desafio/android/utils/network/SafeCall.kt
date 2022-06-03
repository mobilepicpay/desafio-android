package com.picpay.desafio.android.utils.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

object SafeCall {

    suspend fun <T> getResponse(call : suspend () -> Response<T>) : Response<T>? {
        return try {
            withContext(Dispatchers.IO) {
                call.invoke()
            }
        } catch (e: Exception) {
            null
        }
    }
}
