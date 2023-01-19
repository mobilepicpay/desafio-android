package com.picpay.desafio.android

import com.picpay.desafio.android.data.source.remote.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example() {
//        val users = service.getUsers()
        service.getUsers()
//        return users.body() ?: emptyList()
    }
}
