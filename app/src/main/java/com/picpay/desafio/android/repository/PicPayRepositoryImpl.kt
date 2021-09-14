package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class PicPayRepositoryImpl(
    private val api: PicPayService
) : PicPayRepository {

    override suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) { api.getUsers().await() }
    }
}
