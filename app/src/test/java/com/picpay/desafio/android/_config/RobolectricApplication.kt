package com.picpay.desafio.android._config

import android.app.Application
import com.squareup.picasso.Picasso
import org.koin.core.context.startKoin
import org.mockito.Answers
import org.mockito.Mockito

class RobolectricApplication : Application() {

    companion object {
        var isPicassoInitialized = false
    }

    override fun onCreate() {
        super.onCreate()
        if (!isPicassoInitialized) {
            isPicassoInitialized = true
            Picasso.setSingletonInstance(Mockito.mock(Picasso::class.java, Answers.RETURNS_MOCKS))
        }
        startKoin { }
    }
}