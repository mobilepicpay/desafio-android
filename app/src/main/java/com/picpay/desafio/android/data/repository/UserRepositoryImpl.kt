package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userDao: UserDao
) : UserRepository {

    override fun getUser(): Flow<List<UserEntity>> {
        return flow {
            val cached = getCachedUsers()
            if (cached.isNotEmpty()) {
                emit(cached)
            }
            try {
                val result = remoteDataSource.getUsers()
                result.let { list ->
                    userDao.deleteAll()
                    userDao.insertAll(list.filter { it.id != null })
                }
                emit(result)
            } catch (e: Exception) {
                if (cached.isEmpty()) {
                    throw e
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun getCachedUsers(): List<UserEntity> =
        userDao.getAll() ?: listOf()
}
