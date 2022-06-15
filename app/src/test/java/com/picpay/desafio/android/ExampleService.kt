package com.picpay.desafio.android

import com.picpay.desafio.android.data.response.User
import com.picpay.desafio.android.data.service.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<User> {
        return service.getUsers()
    }
}