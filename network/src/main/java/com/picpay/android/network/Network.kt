package com.picpay.android.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> createRetrofitEndPoint(url: String): T {

    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(client)
        .build()
        .create(T::class.java)
}

suspend fun <T> doRequest(
    selectReturnResponseType: SelectReturnDoRequestType = SelectReturnDoRequestType.BODY,
    call: suspend () -> Response<T>
): T {

    return withContext(Dispatchers.IO) {

        val response = try {
            call.invoke()
        } catch (ex: Exception) {
            throw Error.parseException(ex)
        }

        if (!response.isSuccessful) {
            throw Error.parseErrorBody(response.errorBody()?.string(), response.code())
        }

        when (selectReturnResponseType) {
            SelectReturnDoRequestType.HEADERS -> response.headers() as T
            SelectReturnDoRequestType.BODY -> response.body() as T
            SelectReturnDoRequestType.RESPONSE -> response as T
        }
    }
}

enum class SelectReturnDoRequestType() {
    HEADERS, BODY, RESPONSE
}