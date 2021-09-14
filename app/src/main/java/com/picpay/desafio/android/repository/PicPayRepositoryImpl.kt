package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.repository.remote.PicPayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class PicPayRepositoryImpl(
    private val api: PicPayService
) : PicPayRepository {

    override suspend fun getUsersFromRemote(): List<UserResponse> {
        return withContext(Dispatchers.IO) { api.getUsers().await() }
    }
}
