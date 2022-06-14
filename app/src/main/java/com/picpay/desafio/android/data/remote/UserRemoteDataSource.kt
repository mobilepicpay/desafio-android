package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class UserRemoteDataSource(private val api: PicPayApi) {

    private fun <T> call(block: suspend FlowCollector<T>.() -> T): Flow<T> = flow {
        emit(block())
    }

    fun getUsers(): Flow<List<UserEntity>> = call { api.getUsers() }
}