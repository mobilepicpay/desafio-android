package com.picpay.desafio.android.provider

import com.picpay.desafio.android.data.entity.ContactDTO
import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.flowOf

object MockUserProvider {

    fun mockedUser() = internalMockedUser()
    private fun internalMockedUser() =
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        )

    fun mockedFlowUsers() = flowOf(internalMockedUsers())
    fun mockedUsers() = internalMockedUsers()
    private fun internalMockedUsers() = listOf(
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        ),
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            name = "Carli Carroll",
            id = 2,
            username = "Constantin_Sawayn"
        ),
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            name = "Annabelle Reilly",
            id = 3,
            username = "Lawrence_Nader62"
        ),
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            name = "Mrs. Hilton Welch",
            id = 4,
            username = "Tatyana_Ullrich"
        ),
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/5.jpg",
            name = "Ms. Simeon Yost",
            id = 5,
            username = "Yasmine_Von5"
        ),
        ContactModel(
            img = "https://randomuser.me/api/portraits/men/6.jpg",
            name = "Mr. Ewell Reynolds",
            id = 6,
            username = "Alysson50"
        )
    )

    fun mockedUsersDTO() = internalMockedUsersDTO()
    private fun internalMockedUsersDTO() = listOf(
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        ),
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            name = "Carli Carroll",
            id = 2,
            username = "Constantin_Sawayn"
        ),
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            name = "Annabelle Reilly",
            id = 3,
            username = "Lawrence_Nader62"
        ),
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            name = "Mrs. Hilton Welch",
            id = 4,
            username = "Tatyana_Ullrich"
        ),
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/5.jpg",
            name = "Ms. Simeon Yost",
            id = 5,
            username = "Yasmine_Von5"
        ),
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/6.jpg",
            name = "Mr. Ewell Reynolds",
            id = 6,
            username = "Alysson50"
        )
    )

    fun modeckedUserDTO() = internalMockedUserDTO()
    private fun internalMockedUserDTO() =
        ContactDTO(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        )

}