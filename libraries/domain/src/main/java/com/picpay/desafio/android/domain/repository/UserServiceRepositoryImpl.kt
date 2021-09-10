package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.utils.Operation
import com.picpay.desafio.android.domain.database.UserDao
import com.picpay.desafio.android.domain.database.UserEntity
import com.picpay.desafio.android.domain.model.User

class UserServiceRepositoryImpl(
    private val userService: UserService,
    private val userDao: UserDao
) : UserServiceRepository {

    override suspend fun getUsers(): List<User> {

        val response = try {
            Operation.Success(userService.getUsers())
        } catch (throwable: Throwable) {
            Operation.Error(throwable)
        }

        return when (response) {
            is Operation.Success -> {
                val userEntity = response.dataType.map {
                    UserEntity(
                        id = it.id,
                        name = it.name,
                        username = it.username,
                        img = it.img
                    )
                }

                userDao.update(userEntity)

                userEntity.map {
                    User(
                        id = it.id,
                        name = it.name,
                        username = it.username,
                        img = it.img
                    )
                }
            }
            is Operation.Error -> throw response.throwable
        }
    }

}
