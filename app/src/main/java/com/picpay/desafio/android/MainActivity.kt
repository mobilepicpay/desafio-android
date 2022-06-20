package com.picpay.desafio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.picpay.desafio.contact_list.presentation.ContactScreen
import com.picpay.desafio.ui.theme.DesafioandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioandroidTheme {
                ContactScreen()
            }
        }
    }
}