package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.dto.UserResponse
import com.picpay.desafio.android.data.source.remote.PicPayApi

class PicPayRepositoryImpl(private val api: PicPayApi) : PicPayRepository {
    override suspend fun getUsers(): List<UserResponse> = api.getUsers()
}