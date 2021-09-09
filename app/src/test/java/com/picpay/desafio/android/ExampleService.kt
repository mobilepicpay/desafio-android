package com.picpay.desafio.android

class ExampleService(
    private val service: com.picpay.desafio.android.data.api.PicPayService
) {

    fun example(): List<com.picpay.desafio.android.data.model.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}