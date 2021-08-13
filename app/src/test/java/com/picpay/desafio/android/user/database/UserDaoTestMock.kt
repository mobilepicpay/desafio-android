package com.picpay.desafio.android.user.database

class UserDaoTestMock : UserDao {

    private val list = mutableListOf<UserEntity>()

    override suspend fun getAll() = list

    override suspend fun insertAll(users: List<UserEntity>) {
        list.addAll(users)
    }

    override suspend fun deleteAll() {
        list.clear()
    }
}