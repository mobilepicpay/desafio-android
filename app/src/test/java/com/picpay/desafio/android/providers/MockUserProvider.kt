package com.picpay.desafio.android.providers

import com.picpay.desafio.android.data.entities.UserEntity

object MockUserProvider {

    fun mockedUser() = internalMockedUser()
    private fun internalMockedUser() = UserEntity(
        id = 1,
        img = "https://randomuser.me/api/portraits/men/1.jpg",
        name = "Eduardo Santos",
        username = "@eduardo.santos"
    )

    fun mockedUsers() = internalMockedUsers()
    private fun internalMockedUsers() = listOf(
        internalMockedUser(),
        UserEntity(
            id = 2,
            img = "https://randomuser.me/api/portraits/women/2.jpg",
            name = "Marina Coelho",
            username = "@marina.coelho"
        ),
        UserEntity(
            id = 3,
            img = "https://randomuser.me/api/portraits/women/3.jpg",
            name = "Márcia Silva",
            username = "@marcia.silva"
        )
    )
}