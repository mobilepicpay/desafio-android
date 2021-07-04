package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.result.ResultWrapper

interface UserRemoteDataSource {
    suspend fun getUsers(): ResultWrapper<List<User>>
}