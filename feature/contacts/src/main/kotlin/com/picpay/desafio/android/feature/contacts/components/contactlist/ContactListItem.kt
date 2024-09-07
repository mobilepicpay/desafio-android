package com.picpay.desafio.android.feature.contacts.components.contactlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.android.core.model.UserModel
import com.picpay.desafio.android.ui.theme.colorDetail
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier,
    user: UserModel,
    searchString: String
) {
    Row(
        modifier = modifier then Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(
                    end = 24.dp
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                model = user.imgBytes,
                contentDescription = "user image",
                filterQuality = FilterQuality.Low
            )
        }

        Column {

            HighlightedText(
                text = "@${user.username}",
                textColor = Color.White,
                searchString = searchString
            )

            HighlightedText(
                text = user.name,
                textColor = colorDetail,
                searchString = searchString
            )
        }


    }
}

@Composable
private fun HighlightedText(
    text: String,
    textColor: Color,
    highlightColor: Color = picPayGreen.copy(alpha = 0.33f),
    searchString: String
) {
    val annotatedString = buildAnnotatedString {
        if (searchString.isNotEmpty()) {
            var startIndex = 0
            var searchIndex = text.indexOf(searchString, ignoreCase = true)

            while (searchIndex >= 0) {
                if (searchIndex > startIndex) {
                    append(text.substring(startIndex, searchIndex))
                }

                withStyle(
                    style = SpanStyle(
                        background = highlightColor,
                        color = Color.White
                    )
                ) {
                    append(text.substring(searchIndex, searchIndex + searchString.length))
                }

                startIndex = searchIndex + searchString.length
                searchIndex = text.indexOf(searchString, startIndex, ignoreCase = true)
            }

            if (startIndex < text.length) {
                append(text.substring(startIndex))
            }
        } else {
            append(text)
        }
    }

    Text(
        text = annotatedString,
        color = textColor
    )
}