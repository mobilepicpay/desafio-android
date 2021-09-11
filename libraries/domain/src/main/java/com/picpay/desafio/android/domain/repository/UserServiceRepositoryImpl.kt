package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.utils.Operation
import com.picpay.desafio.android.domain.database.UserDao
import com.picpay.desafio.android.domain.database.toEntity
import com.picpay.desafio.android.domain.database.toUser
import kotlinx.coroutines.flow.flow

class UserServiceRepositoryImpl(
    private val userService: UserService,
    private val userDao: UserDao
) : UserServiceRepository {

    override suspend fun getUsers() = flow {
        emit(userDao.getAll().toUser())

        val response = try {
            Operation.Success(userService.getUsers())
        } catch (throwable: Throwable) {
            Operation.Error(throwable)
        }

        when (response) {
            is Operation.Success -> {
                val userEntity = response.dataType.toEntity()
                userDao.update(userEntity)
                emit(userEntity.toUser())
            }
            is Operation.Error -> {
               throw response.throwable
            }
        }
    }

}
