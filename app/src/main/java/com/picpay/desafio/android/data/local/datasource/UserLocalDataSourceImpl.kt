package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.local.db.UserDao
import com.picpay.desafio.android.data.local.mapper.convertToUser
import com.picpay.desafio.android.data.local.mapper.convertToUserDb
import com.picpay.desafio.android.data.model.User

data class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUserData(): List<User> {
        return userDao.getUser().convertToUser()
    }

    override suspend fun setUserLocal(userList: List<User>) {
        val listUser = userList.convertToUserDb()
        listUser.forEach {
            userDao.insertUser(it)
        }
    }
}