package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.domain.User

interface UserRemoteDataSource {

    suspend fun getAllUser(): List<User>

}