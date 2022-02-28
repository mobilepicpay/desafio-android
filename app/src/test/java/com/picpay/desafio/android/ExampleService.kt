package com.picpay.desafio.android

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.domain.entities.UserEntity

class ExampleService(
    private val api: PicPayApi
) {

    fun example(): List<UserEntity> {
        val users = api.getUsers().execute()

        return users.body() ?: emptyList()
    }
}