package com.picpay.desafio.android.user.mock

import com.picpay.desafio.android.user.database.entity.UserEntity
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.service.data.UserResponse

object UserMock {

    fun mockUserResponse(): List<UserResponse> {
        val user =
            UserResponse(
                "",
                "Teste",
                1,
                "@teste"
            )
        return arrayListOf(user)
    }

    fun mockUserEntity(): List<UserEntity> {
        val user =
            UserEntity(
                "",
                "Teste",
                1,
                "@teste"
            )
        return arrayListOf(user)
    }

    fun mockUserSuccessEmpty(): Result<List<User>> = Result.success(emptyList())
    fun mockUserSuccess(): Result<List<User>> = Result.success(mockUserEntity())
    fun mockUserFailure(exception: Throwable): Result<List<User>> =
        Result.failure(exception)
}