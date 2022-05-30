package com.picpay.desafio.android.common.util.networkmonitor

import android.net.NetworkCapabilities
import android.os.Build

fun NetworkCapabilities.networkTransport(): NetworkTransport {
    return when {
        isConnectedToMobileData() -> NetworkTransport.MOBILE_DATA
        isConnectedToWiFi() -> NetworkTransport.WI_FI
        isConnectedToEthernet() -> NetworkTransport.ETHERNET
        else -> NetworkTransport.NONE
    }
}

fun NetworkCapabilities.isConnectedToMobileData(): Boolean {
    return hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}

fun NetworkCapabilities.isConnectedToWiFi(): Boolean {
    return hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
    } else false
}

fun NetworkCapabilities.isConnectedToEthernet(): Boolean {
    return hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}
