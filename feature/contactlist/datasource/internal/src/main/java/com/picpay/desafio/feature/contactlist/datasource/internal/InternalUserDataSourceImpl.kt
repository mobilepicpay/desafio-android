package com.picpay.desafio.feature.contactlist.datasource.internal

import com.picpay.desafio.feature.contactlist.repository.UserInternalDataSource
import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class UserInternalDataSourceImpl(
    private val userDao: UserDao,
    private val userMapper: UserEntityMapperWithDomain
) : UserInternalDataSource {
    override suspend fun getUsers(): List<UserDomain> {
        return userDao.getAllUser().map {
            userMapper.mapFromDomain(it)
        }
    }

    override suspend fun insertUsers(users: List<UserDomain>) {
        val userEntities = users.map {
            userMapper.mapToEntity(it)
        }
        userEntities.forEach {
            userDao.insertAll(it)
        }
    }
}