package com.picpay.desafio.android.espresso

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource
import com.picpay.desafio.android.shared.coroutine.CoroutineService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object IdlingResource {
    private val idlingResource = CountingIdlingResource("AndroidTest", true)

    private val coroutineService = object : CoroutineService {
        override val Main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val IO: CoroutineDispatcher
            get() = Dispatchers.IO

        override fun incrementIdlingResources() {
            idlingResource.increment()
        }

        override fun decrementIdlingResources() {
            idlingResource.decrement()
        }
    }

    fun getCoroutineService() = coroutineService

    fun register() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    fun unregister() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}