package com.picpay.desafio.android

import com.picpay.desafio.android.data.remote.data_source.UserRDS
import com.picpay.desafio.android.data.remote.model.UserRM
import com.picpay.desafio.android.domain.model.User

class ExampleService(
    private val service: UserRDS
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}