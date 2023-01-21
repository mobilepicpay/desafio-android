package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.entity.UserEntity
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userDao: UserDao
) : UserRepository {

    private var cached: List<UserEntity>? = null

    @Suppress("TooGenericExceptionCaught")
    override fun getUser(isGetCacheValues: Boolean): Flow<List<UserEntity>> =
        flow {
            if (isGetCacheValues) {
                try {
                    cached = getCachedUsers()
                    if (cached?.isNotEmpty() == true) {
                        emit(cached!!)
                    }
                } catch (ignore: Exception) {
                }
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
        }

    private fun getCachedUsers(): List<UserEntity> =
        userDao.getAll() ?: listOf()
}
