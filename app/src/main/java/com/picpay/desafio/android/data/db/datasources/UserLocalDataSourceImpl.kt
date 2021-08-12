package com.picpay.desafio.android.data.db.datasources

import com.picpay.desafio.android.data.db.dao.UserDao
import com.picpay.desafio.android.data.db.entities.UserEntity
import com.picpay.desafio.android.data.db.mappers.LocalMapper
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.utils.extensions.handleDatabaseException
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userLocalMapper: LocalMapper<UserEntity, User>,
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun insertUsers(users: List<User>) {
        val userEntities = users.map { user ->
            userLocalMapper.fromModelToEntity(user)
        }

        try {
            userDao.insert(userEntities)
        } catch (throwable: Throwable) {
            val databaseException = throwable.handleDatabaseException()
            throw databaseException
        }
    }

    override suspend fun getUsers(): List<User> {
        return try {
            userDao.getAll().map { userEntity ->
                userLocalMapper.fromEntityToModel(userEntity)
            }
        } catch (throwable: Throwable) {
            val databaseException = throwable.handleDatabaseException()
            throw databaseException
        }
    }
}
