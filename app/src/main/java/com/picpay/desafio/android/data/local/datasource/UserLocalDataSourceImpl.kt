package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.local.db.UserDao
import com.picpay.desafio.android.data.local.mapper.DbUserMapper
import com.picpay.desafio.android.data.local.mapper.UserRemoteMapper
import com.picpay.desafio.android.data.model.User

data class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val userRemoteMapper: UserRemoteMapper,
    private val dbUserMapper: DbUserMapper
): UserLocalDataSource {

    override suspend fun getUserData(): List<User> {
        return userDao.getUser().map {
                userRemoteMapper.convertDbUserToUser(it)
            }
    }

    override suspend fun setUserCache(userResponses: List<User>) {
        userResponses.forEach {
            val dbUser = dbUserMapper.convertUser(it)
                userDao.insertUser(dbUser)
        }
    }
}
