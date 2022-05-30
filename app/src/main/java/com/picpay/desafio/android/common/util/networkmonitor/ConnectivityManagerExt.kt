package com.picpay.desafio.android.common.util.networkmonitor

import android.net.ConnectivityManager
import android.os.Build

fun ConnectivityManager?.networkTransport(): NetworkTransport {
    if (this == null) return NetworkTransport.NONE
    return when {
        isConnectedToMobileData() -> NetworkTransport.MOBILE_DATA
        isConnectedToWifi() -> NetworkTransport.WI_FI
        isConnectedToEthernet() -> NetworkTransport.ETHERNET
        else -> NetworkTransport.NONE
    }
}

fun ConnectivityManager.isConnectedToMobileData(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getNetworkCapabilities(activeNetwork)
            ?.isConnectedToMobileData()
            ?: false
    } else {
        @Suppress("DEPRECATION")
        activeNetworkInfo?.type == ConnectivityManager.TYPE_MOBILE
    }
}

fun ConnectivityManager.isConnectedToWifi(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getNetworkCapabilities(activeNetwork)
            ?.isConnectedToWiFi()
            ?: false
    } else {
        @Suppress("DEPRECATION")
        activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }
}

fun ConnectivityManager.isConnectedToEthernet(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getNetworkCapabilities(activeNetwork)
            ?.isConnectedToEthernet()
            ?: false
    } else {
        @Suppress("DEPRECATION")
        activeNetworkInfo?.type == ConnectivityManager.TYPE_ETHERNET
    }
}
