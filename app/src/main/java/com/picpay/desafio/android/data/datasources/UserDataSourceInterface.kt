package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.network.reponses.ApiResponse
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.flow.Flow

interface UserDataSourceInterface {
    fun getFromRemote(): Flow<ApiResponse<List<User>>>
    fun getFromLocal(): Flow<List<User>?>
    fun saveLocal(users: List<User>?)
    fun removeLocal()
}