package com.picpay.desafio.android

import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.repo.UsersApi

class ExampleService(
    private val service: UsersApi
) {

    fun example(): List<UserResponse> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}