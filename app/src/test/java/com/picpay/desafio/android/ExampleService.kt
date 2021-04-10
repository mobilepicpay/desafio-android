package com.picpay.desafio.android

import com.picpay.desafio.android.gateways.PicPayService
import com.picpay.desafio.android.userlist.User

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}