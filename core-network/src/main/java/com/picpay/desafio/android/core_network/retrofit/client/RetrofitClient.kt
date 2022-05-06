package com.picpay.desafio.android.core_network.retrofit.client

import com.picpay.desafio.android.core_network.models.NetworkError
import com.picpay.desafio.android.core_network.models.Response
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CancellationException

object RetrofitClient {

    /**
     * Builds a Retrofit client given a Retrofit requests Interface using the given baseUrl and OkHttpClient
     * @param baseUrl Base url to be used in the network requests
     * @param httpClient Custom HttpClient to add interceptors or other behaviors to the Retrofit Client, if no client is passed the default builder will be used
     * */
    inline fun <reified T> makeService(
        baseUrl: String,
        httpClient: OkHttpClient = OkHttpClient()
    ): T {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(T::class.java)
    }

    /**
     * Receives a retrofit suspend function and return the request's result. Throws cancellation exception if the parent coroutine is cancelled.
     * @param request Suspend function used to make the HTTP request
     * @throws CancellationException When parent coroutine is cancelled
     * */
    suspend fun <T> makeCall(request: suspend () -> T): Response<T> {
        return try {
            Response.Success(request())
        } catch (e: Throwable) {
            when (e) {
                is CancellationException -> throw e
                is HttpException -> Response.Error(
                    NetworkError(
                        errorMessage = e.message(),
                        errorCode = e.code()
                    )
                )
                else -> Response.Error(
                    NetworkError(
                        errorMessage = "Unexpected Network Error",
                        errorCode = 500
                    )
                )
            }
        }

    }
}