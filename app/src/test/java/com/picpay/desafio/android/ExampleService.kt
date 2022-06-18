package com.picpay.desafio.android

import com.picpay.desafio.android.datasource.remote.UserRDS
import com.picpay.desafio.android.domain.model.User

class ExampleService(
    private val service: UserRDS
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}