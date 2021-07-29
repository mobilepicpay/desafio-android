package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.remote.PicPayService
import com.picpay.desafio.android.data.entity.User

class ContactRepositoryImpl(private val service: PicPayService) : ContactRepository {

    override suspend fun getAll(): List<User> = service.getUsers()
}