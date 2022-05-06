package com.picpay.desafio.android.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.picpay.desafio.android.MainActivity

@Composable
fun UserListScreen() {
    val viewModel = hiltViewModel<UserListViewModel>(LocalContext.current as MainActivity)

    val items = viewModel.list.collectAsState().value
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { user ->
                    Text(text = user.name)
                }
            }
        }
    }

}
