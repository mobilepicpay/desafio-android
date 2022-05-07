package com.picpay.desafio.android.presentation.userlist.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.model.UserPresentable

@Composable
internal fun UserCard(
    modifier: Modifier = Modifier,
    user: UserPresentable
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            UserAvatar(url = user.img)
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.caption
                )
            }
        }

    }
}

@Composable
private fun UserAvatar(
    modifier: Modifier = Modifier,
    url: String
) {
    if (url.isEmpty()) DefaultAvatar(modifier)
    else {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .border(BorderStroke(1.dp, MaterialTheme.colors.onSurface), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // TODO -> SubCompose layouts are crashing, find out why and then migrate to SubcomposeAsyncImage
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

@Composable
private fun DefaultAvatar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(BorderStroke(1.dp, MaterialTheme.colors.onSurface), CircleShape)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = null,
            modifier = modifier.size(60.dp)
        )
    }

}