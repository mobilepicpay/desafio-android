package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.network.PicPayService

interface UserRepository {
    suspend fun getUsers(): List<User>
}

class UserRepositoryImpl(
    private val picPayService: PicPayService
) : UserRepository {
    override suspend fun getUsers(): List<User> = picPayService.getUsers()
}
