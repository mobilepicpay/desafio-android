package com.picpay.desafio.android

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class DesafioAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
) {
    fun navigateTo(route: String) = navController.navigate(route) { launchSingleTop = true }

    fun navigateBack() = navController.popBackStack()

    fun clearBackAndNavigateFromTo(
        currentRoute: String,
        destinationRoute: String
    ) {
        navController.navigate(route = destinationRoute) {
            popUpTo(route = currentRoute) { inclusive = true }
            launchSingleTop = true
        }
    }

    val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}

@Composable
fun rememberDesafioAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): DesafioAppState =
    remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
    ) {
        DesafioAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor
        )
    }