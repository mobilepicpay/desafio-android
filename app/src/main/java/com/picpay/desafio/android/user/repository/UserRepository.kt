package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.network.ResultWrapper
import com.picpay.desafio.android.network.safeDataRequest
import com.picpay.desafio.android.user.database.UserDao
import com.picpay.desafio.android.user.database.toDomain
import com.picpay.desafio.android.user.network.UserService
import com.picpay.desafio.android.user.network.toEntity
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val userService: UserService,
    private val userDao: UserDao
) {

    suspend fun getUsers() = flow {
        emit(userDao.getAll().toDomain())

        when (val result = safeDataRequest { userService.getUsers() }) {
            is ResultWrapper.Success -> {
                val userEntity = result.content.toEntity()
                userDao.deleteAll()
                userDao.insertAll(userEntity)
                emit(userEntity.toDomain())
            }
            is ResultWrapper.Error -> throw result.throwable
        }
    }
}