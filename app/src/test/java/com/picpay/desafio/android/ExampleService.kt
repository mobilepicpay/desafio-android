package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.UserApi

class ExampleService(
    private val service: UserApi
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}