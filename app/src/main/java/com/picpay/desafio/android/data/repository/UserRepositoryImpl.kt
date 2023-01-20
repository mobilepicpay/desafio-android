package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.entity.UserEntity
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

    private var cached: List<UserEntity>? = null

    @Suppress("TooGenericExceptionCaught")
    override fun getUser(): Flow<List<UserEntity>> =
        flow {
            try {
                cached = getCachedUsers()
                if (cached?.isNotEmpty() == true) {
                    emit(cached!!)
                }
            } catch (ignore: Exception) {
            }
            try {
                remoteDataSource.getUsers().filter { it.id != null }.let { list ->
                    userDao.deleteAll()
                    userDao.insertAll(list)
                    emit(list)
                }
            } catch (e: Exception) {
                if (cached.isNullOrEmpty()) {
                    throw e
                }
            }
        }.flowOn(Dispatchers.IO)

    override fun getUpDateUsers(): Flow<List<UserEntity>> =
        flow {
            remoteDataSource.getUsers().filter { it.id != null }.let { list ->
                userDao.deleteAll()
                userDao.insertAll(list)
                emit(list)
            }
        }.flowOn(Dispatchers.IO)

    private fun getCachedUsers(): List<UserEntity> =
        userDao.getAll() ?: listOf()
}
