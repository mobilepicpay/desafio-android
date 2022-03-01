package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.models.UserDb


interface UsersLocalDatasource {
    suspend fun getUsers() : List<UserDb>
    suspend fun saveUsers(users: List<UserDb>)
}