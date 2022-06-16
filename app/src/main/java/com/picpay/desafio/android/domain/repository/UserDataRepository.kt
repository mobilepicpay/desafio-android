package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User
import retrofit2.Call

interface UserDataRepository {
    suspend fun getUsers(): List<User>
}