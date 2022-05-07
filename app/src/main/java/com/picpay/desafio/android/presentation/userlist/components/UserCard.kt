package com.picpay.desafio.android.presentation.userlist.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.picpay.desafio.android.presentation.model.UserPresentable

@Composable
internal fun UserCard(
    modifier: Modifier = Modifier,
    user: UserPresentable
) {
    Text(text = user.name)
}