package com.picpay.desafio.contact_list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.ui.theme.Detail

@Composable
fun ContactItem(imageUrl: String, userName: String, name: String) {
    Surface(color = MaterialTheme.colors.primaryVariant, modifier = Modifier.fillMaxWidth().semantics{
        contentDescription = "ContactItem"
    }) {
        Row {

            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(52.dp)
                    .padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
                    .clip(CircleShape)
                    .semantics {
                        contentDescription = "ContactItemImg"
                    }
            )

            Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)) {
                Text(userName, color = Color.White, modifier = Modifier.semantics {
                    contentDescription = "ContactItemName"
                })
                Text(name, color = Detail, modifier = Modifier.semantics {
                    contentDescription = "ContactItemUserName"
                })
            }
        }
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    ContactItem(
        imageUrl = "https://randomuser.me/api/portraits/men/1.jpg",
        userName = "@kitute",
        name = "bruno henrique"
    )
}