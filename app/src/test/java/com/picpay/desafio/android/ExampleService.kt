package com.picpay.desafio.android

import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.repository.remote.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserResponse> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}