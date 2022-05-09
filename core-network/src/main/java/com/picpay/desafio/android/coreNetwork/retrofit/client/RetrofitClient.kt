package com.picpay.desafio.android.coreNetwork.retrofit.client

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.picpay.desafio.android.coreNetwork.models.NetworkError
import com.picpay.desafio.android.coreNetwork.models.Response
import kotlinx.coroutines.flow.FlowCollector
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CancellationException


object RetrofitClient {

    const val UNEXPECTED_ERROR = "Unexpected Network Error"

    /**
     * Builds a Retrofit client given a Retrofit requests Interface using the given baseUrl and OkHttpClient
     * @param baseUrl Base url to be used in the network requests
     * @param httpClient Custom HttpClient to add interceptors or other behaviors to the Retrofit Client,
     * if no client is passed the default builder will be used
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
     * Receives a retrofit suspend function and return the request's result.
     * Throws cancellation exception if the parent coroutine is cancelled.
     * @param request Suspend function used to make the HTTP request
     * @throws CancellationException When parent coroutine is cancelled
     * */
    @Suppress("TooGenericExceptionCaught")
    suspend fun <T> FlowCollector<Response<T>>.makeCall(request: suspend () -> T) {
        emit(
            try {
                Response.Success(request())
            } catch (e: Exception) {
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
                            errorMessage = UNEXPECTED_ERROR,
                            errorCode = 500
                        )
                    )
                }
            }
        )
    }

}
