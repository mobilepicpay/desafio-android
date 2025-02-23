package com.picpay.desafio.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.presentation.ui.features.contacts.ContactsViewModel
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.ContactsScreenComposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ContactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsScreenComposable(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                screen = viewModel.screen.collectAsState().value,
                listener = viewModel
            )
        }
    }
}
