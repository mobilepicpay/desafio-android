package com.picpay.desafio.android.providers

import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.flowOf

object ContactMockProvider {

    fun mockedFlowContacts() = flowOf(mockedContacts())
    private fun mockedContacts() = internalMockedContacts()
    private fun internalMockedContacts() = listOf(
        UserModel(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Eduardo Santos",
            username = "@eduardo.santos"
        ),
        UserModel(
            img = "https://randomuser.me/api/portraits/woman/2.jpg",
            name = "Marina Coelho",
            username = "@marina.coelho"
        ),
        UserModel(
            img = "https://randomuser.me/api/portraits/woman/3.jpg",
            name = "Márcia Silva",
            username = "@marcia.silva"
        )
    )
}