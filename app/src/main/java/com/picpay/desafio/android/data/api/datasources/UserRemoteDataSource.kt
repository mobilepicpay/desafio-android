package com.picpay.desafio.android.data.api.datasources

import com.picpay.desafio.android.models.User

interface UserRemoteDataSource {

    suspend fun getUsers(): List<User>
}
