package com.picpay.desafio.android

import com.picpay.android.user.usedatasoucer.model.User

class ExampleService(
    private val service: com.picpay.android.user.usedatasoucer.PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}