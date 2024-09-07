package com.picpay.desafio.android

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import com.picpay.desafio.android.core.data.network.test.TestNetworkMonitor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class DesafioAppStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var networkMonitor : TestNetworkMonitor

    private lateinit var state: DesafioAppState
    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var isOffline: State<Boolean>

    @Before
    fun setUp() {
        networkMonitor = TestNetworkMonitor()
        testDispatcher = StandardTestDispatcher()
    }

    @Test
    fun desafioAppState_currentDestination() = runTest(testDispatcher) {
        var currentDestination: String? = null

        composeTestRule.setContent {

            val navController = rememberTestNavController()

            state = remember(navController) {
                DesafioAppState(
                    windowSizeClass = getCompactWindowClass(),
                    navController = navController,
                    networkMonitor = networkMonitor,
                    coroutineScope = backgroundScope
                )
            }

            currentDestination = state.currentDestination?.route

            LaunchedEffect(Unit) {
                navController.setCurrentDestination("b")
            }
        }

        assertEquals("b", currentDestination)
    }

    @Test
    fun stateIsOfflineWhenNetworkMonitorIsOffline() = runTest(testDispatcher) {

        composeTestRule.setContent {
            val context = LocalContext.current
            state = remember {
                DesafioAppState(
                    windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(900.dp, 1200.dp)),
                    navController = NavHostController(context),
                    networkMonitor = networkMonitor,
                    coroutineScope = backgroundScope
                )
            }
        }

        backgroundScope.launch {
            state.isOffline.collect()
            networkMonitor.setConnected(false)

            assertTrue(isOffline.value)

            networkMonitor.setConnected(true)

            assertFalse(isOffline.value)

            networkMonitor.setConnected(false)

            assertTrue(isOffline.value)
        }

    }

    private fun getCompactWindowClass() = WindowSizeClass.calculateFromSize(DpSize(500.dp, 300.dp))
}

@Composable
private fun rememberTestNavController(): TestNavHostController {
    val context = LocalContext.current
    val navController = remember {
        TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            graph = createGraph(startDestination = "a") {
                composable("a") { }
                composable("b") { }
                composable("c") { }
            }
        }
    }
    return navController
}
