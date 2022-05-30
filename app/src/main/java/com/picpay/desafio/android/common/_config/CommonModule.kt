package com.picpay.desafio.android.common._config

import android.app.Application
import com.picpay.desafio.android.common.util.networkmonitor.NetworkStateMonitor
import org.koin.dsl.module

object CommonModule {

    val module = module {
        single { NetworkStateMonitor(get<Application>()).apply { startNetworkObserver() } }
    }
}