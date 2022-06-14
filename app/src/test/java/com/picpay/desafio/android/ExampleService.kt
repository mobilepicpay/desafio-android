package com.picpay.desafio.android

import com.picpay.desafio.android.data.entities.UserEntity
import com.picpay.desafio.android.data.remote.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserEntity> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}