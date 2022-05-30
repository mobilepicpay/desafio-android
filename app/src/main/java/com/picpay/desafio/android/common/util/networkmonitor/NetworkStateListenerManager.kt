package com.picpay.desafio.android.common.util.networkmonitor

interface NetworkStateListenerManager {

    /**
     * Register a listener to network state changes.
     * */
    fun addNetworkStateListener(
        listener: (networkState: NetworkState, networkTransport: NetworkTransport) -> Unit
    )

    /**
     * Remove a listener that has been registered before.
     * */
    fun removeNetworkStateListener(
        listener: (networkState: NetworkState, networkTransport: NetworkTransport) -> Unit
    )
}