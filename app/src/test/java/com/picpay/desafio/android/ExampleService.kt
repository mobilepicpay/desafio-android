package com.picpay.desafio.android

import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.domain.entities.UserEntity

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserEntity> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}