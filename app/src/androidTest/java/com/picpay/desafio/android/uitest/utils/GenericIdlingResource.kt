package com.picpay.desafio.android.uitest.utils

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource

class GenericIdlingResource(val isIdle: () -> Boolean): IdlingResource {

    private var mCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = this.javaClass.name

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        mCallback = callback
    }

    override fun isIdleNow(): Boolean {
        return isIdle().also { if (it) notifyOnTransitionToIdle()  }
    }

    fun register() {
        IdlingRegistry.getInstance().register(this)
    }

    fun unregister() {
        IdlingRegistry.getInstance().unregister(this)
    }

    private fun notifyOnTransitionToIdle() {
        mCallback?.onTransitionToIdle()
    }
}