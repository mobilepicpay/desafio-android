package com.picpay.desafio.android.ui.contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.UserUiData

@Composable
fun ContactsScreen(activityViewModel: ContactsViewModel = viewModel()) {
    val userList by activityViewModel.usersUiData.collectAsState()
    val isLoading by activityViewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
    ) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.padding(start = 24.dp, top = 48.dp, bottom = 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.title),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            },
            containerColor = colorResource(R.color.colorPrimaryDark)
        ) { paddingValues ->
            UserList(userList, paddingValues)

            if(isLoading) {
                ProgressBar()
            }
        }
    }
}

@Composable
fun UserList(
    userList: List<UserUiData>,
    paddingValues: PaddingValues
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(userList) { user ->
            UserItem(user)
        }
    }

}

@Composable
fun UserItem(
    user: UserUiData
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (user.imgBitmap != null) {
            Image(
                bitmap = user.imgBitmap.asImageBitmap(),
                contentDescription = "Imagem do usuário",
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .size(52.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.ic_round_account_circle),
                contentDescription = "Ícone de usuário",
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .size(52.dp)
            )
        }

        Column {
            Text(
                text = "@${user.username}",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, end = 16.dp)
            )
            Text(
                text = user.name,
                color = colorResource(R.color.colorDetail),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun ProgressBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {},
            )
            .fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            color = Color.Green,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun ContactsScreenPreview() {
    ContactsScreen()
}