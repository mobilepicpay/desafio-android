package com.picpay.desafio.android.data._config.network

import com.picpay.desafio.android.common.util.networkmonitor.NetworkState
import com.picpay.desafio.android.common.util.networkmonitor.NetworkStateMonitor
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor(private val networkStateMonitor: NetworkStateMonitor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()
        val request = when (networkStateMonitor.networkState) {
            NetworkState.OFFLINE -> currentRequest.cacheAlways()
            NetworkState.ONLINE -> currentRequest.noCache()
        }
        return chain.proceed(request)
    }

    private fun Request.cacheAlways(): Request {
        val cacheControl: CacheControl = CacheControl.Builder()
            .maxStale(7, TimeUnit.DAYS)
            .build()
        return newBuilder()
            .cacheControl(cacheControl)
            .build()
    }

    private fun Request.noCache(): Request {
        return newBuilder()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
    }

}