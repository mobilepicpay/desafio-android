package com.picpay.desafio.android.presentation.ui.features.contacts.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.features.contacts.model.ContactsScreen
import com.picpay.desafio.android.presentation.ui.features.contacts.ContactsListener
import com.picpay.desafio.android.presentation.ui.features.contacts.DummyContactsListener
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.fakedata.ContactsScreenFakeData
import com.picpay.desafio.android.presentation.ui.utils.compose.ScreenComposable

internal const val ERROR_TAG = "error"
internal const val LOADING_TAG = "loading"
internal const val SUCCESS_TAG = "success"
internal const val LOADING_IMAGE_TAG = "loading_image"
internal const val CACHE_TAG = "cache_tag"

@Composable
fun ContactsScreenComposable(
    screen: ContactsScreen,
    listener: ContactsListener,
    modifier: Modifier = Modifier,
) {
    ScreenComposable(
        screenState = screen.state,
        listener = listener,
        toolbar = {
            Text(
                modifier = Modifier.padding(24.dp),
                text = stringResource(R.string.title),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        successContent = {
            Column(
                modifier = modifier
                    .testTag(SUCCESS_TAG)
            ) {
                screen.contacts?.let { contacts ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = contacts,
                            key = { it.id }
                        ) { contact ->
                            ContactComposable(
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                                    .fillMaxWidth(),
                                contact = contact,
                            )
                        }
                    }
                }
            }
        }
    )
}


@Composable
@Preview("ContactsScreenPreview")
private fun ContactsScreenPreview(
    @PreviewParameter(ContactsScreenFakeData::class) data: ContactsScreen
) {
    ContactsScreenComposable(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        screen = data,
        listener = DummyContactsListener,
    )
}