package com.picpay.desafio.android.data.database

interface UserDatabaseRepository {

    suspend fun insert(users: List<UserEntity>)

    suspend fun getUsers(): List<UserEntity>
}