package com.picpay.desafio.android.resources

import com.picpay.desafio.android.domain.entities.UserEntity

object UserResources {

    fun getDummyUsers() = listOf(
        UserEntity("", "baseName", 1, "baseUserName"),
        UserEntity("", "baseName", 2, "baseUserName"),
        UserEntity("", "baseName", 3, "baseUserName"),
        UserEntity("", "baseName", 4, "baseUserName"),
        UserEntity("", "baseName", 5, "baseUserName")
    )

    fun getEmptyList() = listOf<UserEntity>()

    fun getDummyException() = Exception("Dummy Exception")

}