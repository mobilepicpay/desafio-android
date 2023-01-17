package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.mapper.UserMapper
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userDao: UserDao,
    private val mapper: UserMapper
) : UserRepository {

    override fun getUser(): Flow<Outcome<List<UserEntity>>?> {
        return flow {
            emit(Outcome.Loading())
            var result: Outcome<List<UserEntity>>? = remoteDataSource.getUsers()
            if (result is Outcome.Success) {
                result.data.let { list ->
                    userDao.deleteAll()
                    userDao.insertAll(list.filter { it.id != null })
                }
            } else {
                result = getCachedUsers()
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun getCachedUsers(): Outcome<List<UserEntity>>? =
        userDao.getAll()?.let { list ->
            Outcome.Success(list)
        }
}
