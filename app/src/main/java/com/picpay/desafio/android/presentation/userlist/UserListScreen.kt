package com.picpay.desafio.android.presentation.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.picpay.desafio.android.MainActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.model.UserPresentable
import com.picpay.desafio.android.presentation.userlist.UserListTestTag.USER_LAZY_COLUMN
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
            UserListStateContent(
                state = viewModel.screenState.collectAsState().value,
                tryAgain = { viewModel.retryRequest() }
            )
        }

    }
}

@Composable
private fun UserListStateContent(
    tryAgain: () -> Unit,
    state: UserListState
) {
    when (state) {
        is UserListState.Loading -> UserListLoading(modifier = Modifier.fillMaxSize())
        is UserListState.Error -> UserListError(tryAgain)
        is UserListState.Ready -> UserListReady(
            users = state.users,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun UserListLoading(
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
        modifier = modifier.testTag(USER_LAZY_COLUMN),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(users) { user ->
            UserCard(
                modifier = Modifier.fillMaxWidth(),
                user = user
            )
        }
        item {
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun UserListError(
    tryAgain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.error))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = tryAgain
        ) {
            Text(stringResource(id = R.string.try_again))
        }

    }
}
