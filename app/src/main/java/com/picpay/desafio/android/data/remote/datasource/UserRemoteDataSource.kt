package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.model.User

interface UserRemoteDataSource {
    suspend fun getUser(): List<User>
}