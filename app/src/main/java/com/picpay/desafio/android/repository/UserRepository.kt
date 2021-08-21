package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.UserDTO

interface UserRepository {

    suspend fun getUsers() : List<UserDTO>
}