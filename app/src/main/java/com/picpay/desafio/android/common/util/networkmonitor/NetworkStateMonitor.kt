package com.picpay.desafio.android.common.util.networkmonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Handler
import android.os.Looper
import com.picpay.desafio.android.common.util.networkmonitor.NetworkTransport.Companion.toState

class NetworkStateMonitor(private val context: Context) : NetworkStateListenerManager {

    var networkState: NetworkState = NetworkState.OFFLINE
    var networkTransport: NetworkTransport = NetworkTransport.NONE

    /**
     * Saves the initial state of the monitor when services start.
     * */
    private var initialNetworkState: NetworkState = NetworkState.OFFLINE
    private var isInitialNetworkStateHandled = false

    private val networkStateListener = mutableListOf<(
        networkState: NetworkState,
        networkTransport: NetworkTransport
    ) -> Unit>()

    override fun addNetworkStateListener(
        listener: (networkState: NetworkState, networkTransport: NetworkTransport) -> Unit
    ) {
        networkStateListener.add(listener)
    }

    override fun removeNetworkStateListener(
        listener: (networkState: NetworkState, networkTransport: NetworkTransport) -> Unit
    ) {
        networkStateListener.remove(listener)
    }

    /**
     * Request the connectivity system service to track network changes.
     * */
    private val connectivityManagerService by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    }

    /**
     * Broadcast receiver used to dispatch network changes in api 21 ~ 22.
     * */
    private val networkBroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                onNetworkTransportChanged(connectivityManagerService.networkTransport())
            }
        }
    }

    /**
     * Setup the network request to track connection by connection capabilities.
     * */
    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .build()
    }

    /**
     * Setup the network callback to bring info about the network status.
     * */
    private val networkCallback: ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {

            /**
             * When [NetworkCapabilities] has changed the current method is called with the active
             * [network], when there are no more [network] available, this method will not be called
             * and the connection change treatment will be done by [onLost].
             *
             * */
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                onNetworkTransportChanged(networkCapabilities.networkTransport())
            }

            override fun onUnavailable() {
                onNetworkTransportChanged(connectivityManagerService.networkTransport())
            }

            /**
             * When [network] is no longer available this method is called.
             * */
            override fun onLost(network: Network) {
                onNetworkTransportChanged(connectivityManagerService.networkTransport())
            }
        }
    }

    /**
     * Starts observing the network state changes.
     * */
    fun startNetworkObserver() {
        // when services starts, save the initial state
        initialNetworkState = connectivityManagerService.networkTransport().toState()
        networkState = initialNetworkState
        isInitialNetworkStateHandled = false
        // start network changes callback
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManagerService?.registerNetworkCallback(networkRequest, networkCallback)
            } else {
                @Suppress("DEPRECATION")
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).also {
                    context.registerReceiver(networkBroadcastReceiver, it)
                }
            }
        } catch (e: Exception) {
            // TODO: log exception to crashlytics
        }
    }

    /**
     * Stops observing the network state changes.
     * */
    fun stopNetworkObserver() {
        // stop network changes callback
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManagerService?.unregisterNetworkCallback(networkCallback)
            } else {
                context.unregisterReceiver(networkBroadcastReceiver)
            }
        } catch (e: Exception) {
        }
    }

    /**
     * When a [newNetworkTransport] appears, is checked if there has been any change based on the
     * previous state, if there has been any change when the state has occurred a callback is
     * generated for the observers.
     *
     * @param newNetworkTransport the new transport used by network
     *
     * */
    private fun onNetworkTransportChanged(newNetworkTransport: NetworkTransport) {
        // check if state has been changed comparing with the last state
        val networkStateHasChanged = networkState != newNetworkTransport.toState()
        // update connection state and type, with the new values
        networkState = newNetworkTransport.toState()
        networkTransport = newNetworkTransport
        // check if the first network state changed is actually the current state
        if (!isInitialNetworkStateHandled) {
            isInitialNetworkStateHandled = true
            // return to avoid send callback to listeners if the initial state is equals to actual
            if (initialNetworkState == networkState) return
        }
        // if has been detected some change in state, dispatch to observers
        if (networkStateHasChanged) dispatchNetworkStateChanges(networkState, networkTransport)
    }

    /**
     * Notify observers about the network changed state.
     * */
    private fun dispatchNetworkStateChanges(
        networkState: NetworkState,
        networkTransport: NetworkTransport
    ) {
        // change to main thread
        Handler(Looper.getMainLooper()).post {
            networkStateListener.forEach { it.invoke(networkState, networkTransport) }
        }
    }
}