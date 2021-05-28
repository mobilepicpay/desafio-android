package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.db.UserDao
import com.picpay.desafio.android.data.mappers.DbUserToUserMapper
import com.picpay.desafio.android.data.mappers.UserToDbUserMapper
import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserLocalDataSourceImp(
    private val userDao: UserDao,
    private val dbUserToUserMapper: DbUserToUserMapper,
    private val userToDbUserMapper: UserToDbUserMapper) : UserLocalDataSource {

    override suspend fun getAllUsers(): List<User> {

        return withContext(Dispatchers.IO) {
            userDao.getAllUsers().map {
                dbUserToUserMapper.convertDbUserToUser(it)
            }
        }
    }

    override suspend fun updateCache(users: List<User>) {

        users.forEach {
            val dbUser = userToDbUserMapper.convertUserToDbUser(it)
            withContext(Dispatchers.IO) {
                userDao.insertUser(dbUser)
            }
        }
    }
}