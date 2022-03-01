package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.models.UserResponse


interface UsersLocalDatasource {
    suspend fun getUsers() : List<UserResponse>
    suspend fun saveUsers(users: List<UserResponse>)
}