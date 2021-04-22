package com.picpay.desafio.android.persistance.repository

import com.picpay.desafio.android.persistance.dao.UserDao
import com.picpay.desafio.android.persistance.mappers.toDomain
import com.picpay.desafio.android.persistance.mappers.toEntity
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.UserCacheRepository

class UserCacheRepositoryImp(private val dao: UserDao) : UserCacheRepository {

    override suspend fun save(users: List<User>) {
        dao.insertAll(users.toEntity())
    }

    override suspend fun cached(): List<User> {
          return dao.getAllUsers().toDomain()
        }
    }