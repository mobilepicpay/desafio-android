package com.picpay.desafio.feature.contactlist.repository.impl

import com.picpay.desafio.feature.contactlist.repository.UserInternalDataSource
import com.picpay.desafio.feature.contactlist.repository.UserRemoteDataSource
import com.picpay.desafio.feature.contactlist.repository.UserRepository
import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val internalDataSource: UserInternalDataSource
) : UserRepository {
    override suspend fun getUsers(): Result<List<UserDomain>> {
        return try {
            val usersFromDataBase = internalDataSource.getUsers()
            if (usersFromDataBase.isEmpty()) {
                val usersFromRemote = remoteDataSource.getUsers()
                internalDataSource.insertUsers(usersFromRemote)
                Result.success(usersFromRemote)
            } else {
                Result.success(usersFromDataBase)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertUsers(users: List<UserDomain>): Result<Unit> {
        return try {
            internalDataSource.insertUsers(users)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}