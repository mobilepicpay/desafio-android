package com.picpay.desafio.android.data.source

import com.picpay.desafio.android.data.response.User
import com.picpay.desafio.android.data.service.PicPayService

class UserSource(private val picPayService: PicPayService) {
    suspend fun load(): List<User> {
        return picPayService.getUsers()
    }
}