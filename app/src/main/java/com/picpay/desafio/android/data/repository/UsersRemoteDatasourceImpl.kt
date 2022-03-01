package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.PicPayApi


class UsersRemoteDatasourceImpl(private val service: PicPayApi) : UsersRemoteDatasource {
    override suspend fun getUsers() = service.getUsers()
}