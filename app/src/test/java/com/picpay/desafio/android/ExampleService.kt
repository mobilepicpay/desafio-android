package com.picpay.desafio.android

import com.picpay.desafio.android.data.entity.User
import com.picpay.desafio.android.data.api.remote.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}