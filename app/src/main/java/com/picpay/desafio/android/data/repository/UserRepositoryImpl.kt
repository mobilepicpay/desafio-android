package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.remote.PicPayService
import com.picpay.desafio.android.data.entity.User

class UserRepositoryImpl(private val service: PicPayService) : UserRepository {

    override suspend fun getAll(): List<User> = service.getUsers()
}