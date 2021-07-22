package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.User

interface UserDataRepository {
    suspend fun getUserList(): List<User>
    suspend fun saveUserCache(userList: List<User>)
    suspend fun getUserLocal(): List<User>
}