package com.picpay.desafio.android.provider

import com.picpay.desafio.android.data.entity.ContactDTO
import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.flowOf

object MockContactProvider {
    fun mockedContact() = internalMockedContact()
    private fun internalMockedContact() =
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        )

    fun mockedFlowContacts() = flowOf(internalMockedContacts())
    fun mockedContacts() = internalMockedContacts()
    private fun internalMockedContacts() = listOf(
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        ),
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/2.jpg",
            name = "Carli Carroll",
            id = 2,
            username = "Constantin_Sawayn"
        ),
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/3.jpg",
            name = "Annabelle Reilly",
            id = 3,
            username = "Lawrence_Nader62"
        ),
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/4.jpg",
            name = "Mrs. Hilton Welch",
            id = 4,
            username = "Tatyana_Ullrich"
        ),
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/5.jpg",
            name = "Ms. Simeon Yost",
            id = 5,
            username = "Yasmine_Von5"
        ),
        ContactModel(
            img = "https://randomContact.me/api/portraits/men/6.jpg",
            name = "Mr. Ewell Reynolds",
            id = 6,
            username = "Alysson50"
        )
    )

    fun mockedContactsDTO() = internalMockedContactsDTO()
    private fun internalMockedContactsDTO() = listOf(
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        ),
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/2.jpg",
            name = "Carli Carroll",
            id = 2,
            username = "Constantin_Sawayn"
        ),
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/3.jpg",
            name = "Annabelle Reilly",
            id = 3,
            username = "Lawrence_Nader62"
        ),
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/4.jpg",
            name = "Mrs. Hilton Welch",
            id = 4,
            username = "Tatyana_Ullrich"
        ),
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/5.jpg",
            name = "Ms. Simeon Yost",
            id = 5,
            username = "Yasmine_Von5"
        ),
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/6.jpg",
            name = "Mr. Ewell Reynolds",
            id = 6,
            username = "Alysson50"
        )
    )

    fun mockedContactDTO() = internalMockedContactDTO()
    private fun internalMockedContactDTO() =
        ContactDTO(
            img = "https://randomContact.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        )
}