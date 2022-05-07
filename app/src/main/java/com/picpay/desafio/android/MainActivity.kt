package com.picpay.desafio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.picpay.desafio.android.core_ui.theme.PicpayTheme
import com.picpay.desafio.android.presentation.userlist.UserListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            PicpayTheme {
                val systemUi = rememberSystemUiController()
                val colors = MaterialTheme.colors

                SideEffect {
                    systemUi.setStatusBarColor(
                        color = colors.primary
                    )
                }

                UserListScreen()
            }
        }
    }

}
