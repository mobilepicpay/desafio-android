package com.picpay.desafio.android

import com.picpay.android.user.api.model.User

class ExampleService(
    private val service: com.picpay.android.user.api.PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}