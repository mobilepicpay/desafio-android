package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.utils.service.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}