package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

internal interface PicpayLocalDataSource {

    suspend fun getUsers(): Flow<Response<List<UserEntity>>>

    suspend fun saveUsers(users: List<UserEntity>)

    fun checkCacheExpired(): Boolean

}