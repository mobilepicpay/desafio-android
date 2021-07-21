package com.picpay.desafio.android

import com.picpay.desafio.android.model.network.User
import com.picpay.desafio.android.retrofit.service.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}