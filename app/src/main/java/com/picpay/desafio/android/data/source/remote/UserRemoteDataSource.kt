package com.picpay.desafio.android.data.source.remote

import com.picpay.desafio.android.data.entity.UserEntity

interface UserRemoteDataSource {
    suspend fun getUsers(): List<UserEntity>
}
