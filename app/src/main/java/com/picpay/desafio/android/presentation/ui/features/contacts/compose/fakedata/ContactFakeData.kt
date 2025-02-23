package com.picpay.desafio.android.presentation.ui.features.contacts.compose.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.picpay.desafio.android.domain.features.contacts.model.Contact

class ContactFakeData : PreviewParameterProvider<Contact> {
    private fun contact(id: Int): Contact {
        return Contact(
            id = id,
            name = "Rodrigo Murta",
            username = "rodrigo.mmurta$id",
            image = "https://randomuser.me/api/portraits/men/${id + 1}.jpg"
        )
    }

    override val values: Sequence<Contact>
        get() = List(size = 10) { index ->
            contact(index)
        }.asSequence()
}