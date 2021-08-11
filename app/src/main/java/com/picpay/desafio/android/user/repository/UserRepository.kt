package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.network.ResultWrapper
import com.picpay.desafio.android.network.safeDataRequest
import com.picpay.desafio.android.user.database.UserDao
import com.picpay.desafio.android.user.database.toDomain
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.network.UserService
import com.picpay.desafio.android.user.network.toEntity

class UserRepository(
    private val userService: UserService,
    private val userDao: UserDao
) {

    suspend fun getUsers(callback: (List<UserDomain>) -> Unit) {
        callback(userDao.getAll().toDomain())

        when (val result = safeDataRequest { userService.getUsers() }) {
            is ResultWrapper.Success -> {
                val userEntity = result.content.toEntity()
                userDao.deleteAll()
                userDao.insertAll(userEntity)
                callback(userEntity.toDomain())
            }
            is ResultWrapper.Error -> {}//TODO add error case
        }
    }
}