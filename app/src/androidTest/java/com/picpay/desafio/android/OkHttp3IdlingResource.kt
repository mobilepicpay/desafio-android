package com.picpay.desafio.android

import androidx.annotation.CheckResult
import androidx.annotation.NonNull
import androidx.test.espresso.IdlingResource
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

// From https://github.com/technoir42/okhttp-idling-resource/blob/d2eb4d2af9c4221064438f8d0acbffd332a183ca/src/main/java/com/jakewharton/espresso/OkHttp3IdlingResource.java
class OkHttp3IdlingResource private constructor(private val name: String, dispatcher: Dispatcher) :
    IdlingResource {
    private val dispatcher: Dispatcher

    @Volatile
    var callback: IdlingResource.ResourceCallback? = null

    init {
        this.dispatcher = dispatcher
        dispatcher.idleCallback =
            Runnable {
                val callback = callback
                callback?.onTransitionToIdle()
            }
    }

    override fun getName(): String {
        return name
    }

    override fun isIdleNow(): Boolean {
        return dispatcher.runningCallsCount() === 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    companion object {
        /**
         * Create a new [IdlingResource] from `client` as `name`. You must register
         * this instance using `Espresso.registerIdlingResources`.
         */
        @CheckResult
        @NonNull // Extra guards as a library.
        fun create(@NonNull name: String?, @NonNull client: OkHttpClient?): OkHttp3IdlingResource {
            if (name == null) throw NullPointerException("name == null")
            if (client == null) throw NullPointerException("client == null")
            return OkHttp3IdlingResource(name, client.dispatcher)
        }
    }
}
