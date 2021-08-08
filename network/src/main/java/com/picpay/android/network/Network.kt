package com.picpay.android.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> createRetrofitEndPoint(
    url: String,
    interceptList: List<Interceptor> = mutableListOf()
): T {

    val client = OkHttpClient.Builder().apply {

        if(interceptList.isNotEmpty())
            interceptors().addAll(interceptList)

        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(client)
        .build()
        .create(T::class.java)
}

suspend fun <T> doRequest(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    selectReturnResponseType: SelectReturnDoRequestType = SelectReturnDoRequestType.BODY,
    call: suspend () -> Response<T>
): T {


    return withContext(dispatcher) {

        val response = try {
            call.invoke()
        } catch (ex: Exception) {
            throw CustomError .parseException(ex)
        }

        if (!response.isSuccessful) {
            throw CustomError.parseErrorBody(response.errorBody()?.string(), response.code())
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