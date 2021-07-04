package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.mapper.UserLocalMapper
import com.picpay.desafio.android.data.local.model.UserLocal
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.result.ResultWrapper

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {
    override suspend fun getUsers(): ResultWrapper<List<User>> {
        val response = userDao.getAll()
        return if (response.isNotEmpty())
            ResultWrapper.Success(UserLocalMapper.fromUserLocalList(response))
        else
            ResultWrapper.Error(null)
    }

    override suspend fun updateUsers(usersList: List<UserLocal>) {
        userDao.updateData(usersList)
    }
}