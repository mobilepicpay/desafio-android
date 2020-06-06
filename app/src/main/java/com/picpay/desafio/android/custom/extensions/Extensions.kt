package com.picpay.desafio.android.custom.extensions

import kotlinx.coroutines.Job
import okhttp3.Call
import okhttp3.Request
import retrofit2.Retrofit

@PublishedApi
internal inline fun Retrofit.Builder.callFactory(crossinline body: (Request) -> Call) =
    callFactory(object : Call.Factory {
        override fun newCall(request: Request): Call = body(request)
    })

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}
