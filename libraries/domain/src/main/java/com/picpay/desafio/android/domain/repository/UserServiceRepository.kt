package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User

interface UserServiceRepository {

    suspend fun getUsers(): List<User>

}
