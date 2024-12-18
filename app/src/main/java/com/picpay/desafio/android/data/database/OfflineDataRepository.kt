package com.picpay.desafio.android.data.database

class OfflineDataRepository(private val userDao: UserDao) : UserDatabaseRepository {

    override suspend fun insert(users: List<UserEntity>) {
        userDao.insert(users)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }
}