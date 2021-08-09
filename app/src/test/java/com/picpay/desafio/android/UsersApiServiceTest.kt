package com.picpay.desafio.android

import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.repo.UsersApi

class UsersApiServiceTest(
    private val service: UsersApi
) {

    suspend fun getUsers(): List<UserResponse> {
        val users = service.getUsers()

        return users.body() ?: emptyList()
    }
}