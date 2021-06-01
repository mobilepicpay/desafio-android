package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.User

interface UserRepository {

    suspend fun getAllUser(): List<User>

}