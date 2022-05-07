package com.picpay.desafio.android.presentation.userlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.picpay.desafio.android.MainActivity
import com.picpay.desafio.android.presentation.model.UserPresentable
import com.picpay.desafio.android.presentation.userlist.components.UserCard

@Composable
fun UserListScreen() {
    val viewModel = hiltViewModel<UserListViewModel>(LocalContext.current as MainActivity)

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Contacts",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            UserListStateContent(state = viewModel.screenState.collectAsState().value)
        }

    }
}

@Composable
private fun UserListStateContent(
    state: UserListState
) {
    when (state) {
        is UserListState.Loading -> UserListLoading(modifier = Modifier.fillMaxSize())
        is UserListState.Error -> UserListError(modifier = Modifier.fillMaxSize())
        is UserListState.Ready -> UserListReady(
            users = state.users,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun UserListLoading(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun UserListReady(
    modifier: Modifier = Modifier,
    users: List<UserPresentable>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(users) { user ->
            UserCard(user = user)
        }
        item {
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun UserListError(
    modifier: Modifier = Modifier
) {
    // Figure this later
}