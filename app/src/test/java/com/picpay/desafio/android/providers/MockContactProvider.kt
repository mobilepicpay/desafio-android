package com.picpay.desafio.android.providers

import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.flowOf

object MockContactProvider {

    fun mockedContact() = internalMockedContact()
    private fun internalMockedContact() = ContactModel(
        image = "https://randomuser.me/api/portraits/men/1.jpg",
        name = "Eduardo Santos",
        username = "@eduardo.santos"
    )

    fun mockedFlowContacts() = flowOf(mockedContacts())
    fun mockedContacts() = internalMockedContacts()
    private fun internalMockedContacts() = listOf(
        internalMockedContact(),
        ContactModel(
            image = "https://randomuser.me/api/portraits/woman/2.jpg",
            name = "Marina Coelho",
            username = "@marina.coelho"
        ),
        ContactModel(
            image = "https://randomuser.me/api/portraits/woman/3.jpg",
            name = "Márcia Silva",
            username = "@marcia.silva"
        )
    )
}