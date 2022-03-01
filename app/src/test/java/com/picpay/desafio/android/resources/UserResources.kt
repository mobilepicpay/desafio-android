package com.picpay.desafio.android.resources

import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.domain.entities.UserEntity

object UserResources {

    val dummyListUserEntity = listOf(
        UserEntity("", "baseName", 1, "baseUserName"),
        UserEntity("", "baseName", 2, "baseUserName"),
        UserEntity("", "baseName", 3, "baseUserName"),
        UserEntity("", "baseName", 4, "baseUserName"),
        UserEntity("", "baseName", 5, "baseUserName")
    )
    val dummyListUserResponse = listOf(
        UserResponse(1, "baseName", "baseUserName", ""),
        UserResponse(2, "baseName", "baseUserName", ""),
        UserResponse(3, "baseName", "baseUserName", ""),
        UserResponse(4, "baseName", "baseUserName", ""),
        UserResponse(5, "baseName", "baseUserName", "")
    )
    val emptyListUserEntity = listOf<UserEntity>()
    val emptyListUserResponse = listOf<UserResponse>()

    fun getDummyException() = Exception("Dummy Exception")
}