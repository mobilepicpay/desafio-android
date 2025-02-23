package com.picpay.desafio.android.presentation.ui.features.contacts.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.features.contacts.model.Contact
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.fakedata.ContactFakeData

const val IMAGE_TAG = "image"
const val USERNAME_TAG = "username"
const val NAME_TAG = "name"

@Composable
fun ContactComposable(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .testTag(IMAGE_TAG),
            model = ImageRequest.Builder(LocalContext.current)
                .data(contact.image)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .transformations(RoundedCornersTransformation(radius = 16f))
                .build(),
            placeholder = painterResource(R.drawable.ic_round_account_circle),
            contentDescription = stringResource(R.string.image_profile_description),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                modifier = Modifier.testTag(USERNAME_TAG),
                text = contact.username,
                color = Color.White
            )
            Text(
                modifier = Modifier.testTag(NAME_TAG),
                text = contact.name,
                color = colorResource(R.color.colorDetail)
            )
        }
    }
}

@Composable
@Preview("ContactPreview")
private fun ContactPreview(
    @PreviewParameter(ContactFakeData::class) contact: Contact
) {
    ContactComposable(
        modifier = Modifier.wrapContentSize(),
        contact = contact
    )
}