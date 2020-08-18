package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.user.database.UserDataBase
import com.picpay.desafio.android.user.database.entity.UserEntity
import com.picpay.desafio.android.user.exception.UserDatabaseException
import com.picpay.desafio.android.user.exception.UserServiceException
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.service.UserService
import com.picpay.desafio.android.user.service.data.UserResponse
import com.picpay.desafio.android.user.service.data.mapResponseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImplement(
    private val service: UserService,
    private val dataBase: UserDataBase
) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        insertUserCache(getUserService().mapResponseEntity())
        return getUsersDatabase()
    }

    override suspend fun getUsersCache(): Result<List<User>> {
        return getUsersDatabase()
    }

    private suspend fun getUserService() = withContext(Dispatchers.IO) {
        try {
            service.getUsers()
        } catch (exception: Exception) {
            throw UserServiceException(
                exception
            )
        }
    }

    private suspend fun getUsersDatabase(): Result<List<User>> = withContext(Dispatchers.IO) {
        try {
            Result.success(dataBase.userDAO().getAllUsers())
        } catch (exception: Exception) {
            Result.failure<List<UserResponse>>(
                UserDatabaseException(exception)
            )
        }
    }

    private suspend fun insertUserCache(users: List<UserEntity>) = withContext(Dispatchers.IO) {
        dataBase.userDAO().deleteAndInsert(users)
    }
}