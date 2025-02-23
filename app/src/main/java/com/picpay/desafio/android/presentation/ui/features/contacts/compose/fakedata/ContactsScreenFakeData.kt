package com.picpay.desafio.android.presentation.ui.features.contacts.compose.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.picpay.desafio.android.domain.features.contacts.model.Contact
import com.picpay.desafio.android.domain.features.contacts.model.ContactsScreen
import com.picpay.desafio.android.domain.utils.ErrorInformation
import com.picpay.desafio.android.domain.utils.State

class ContactsScreenFakeData : PreviewParameterProvider<ContactsScreen> {
    private fun contact(id: Int): Contact {
        return Contact(
            id = id,
            name = "Rodrigo Murta",
            username = "rodrigo.mmurta$id",
            image = "https://randomuser.me/api/portraits/men/${id + 1}.jpg"
        )
    }

    private fun listContacts(size: Int): List<Contact> = List(size) { index ->
        contact(index)
    }

    private val successScreen = ContactsScreen(
        state = State.Success(data = listContacts(size = 10)),
        contacts = listContacts(size = 10)
    )

    private val loadingScreen = successScreen.copy(state = State.Loading)

    private val errorWithCacheScreen = successScreen.copy(
        state = State.ErrorWithCache(
            error = ErrorInformation(
                message = "Não foi possível conectar ao servidor. Exibindo dados em cache."
            )
        )
    )

    private val errorWithoutCacheScreen = ContactsScreen(
        state = State.Error(
            error = ErrorInformation(
                message = "Ocorreu um erro, tente novamente."
            )
        ),
        contacts = null,
    )

    override val values: Sequence<ContactsScreen>
        get() = sequenceOf(
            successScreen,
            loadingScreen,
            errorWithCacheScreen,
            errorWithoutCacheScreen
        )
}