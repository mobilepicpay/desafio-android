package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.local.model.UserLocal
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.result.ResultWrapper

interface UserLocalDataSource {
    suspend fun getUsers(): ResultWrapper<List<User>>
    suspend fun updateUsers(usersList: List<UserLocal>)
}