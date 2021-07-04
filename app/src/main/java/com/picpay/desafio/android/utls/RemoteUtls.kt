package com.picpay.desafio.android.utls

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class RemoteUtls private constructor() {

    companion object {
        fun isConnectionAvailable(context: Context): Boolean {
            var isAvailable = false
            val connectionManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities =
                    connectionManager.activeNetwork ?: return false
                val actNw =
                    connectionManager.getNetworkCapabilities(networkCapabilities) ?: return false

                isAvailable = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false

                }
            } else {
                connectionManager.run {
                    connectionManager.activeNetworkInfo?.run {
                        isAvailable = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false

                        }
                    }
                }
            }

            return isAvailable
        }
    }
}