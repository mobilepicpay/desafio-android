package com.picpay.desafio.android.remote.repository

import com.picpay.desafio.android.entities.UsersDomain
import com.picpay.desafio.android.local.mapper.UsersCacheMapper
import com.picpay.desafio.android.local.source.UsersCacheDataSource
import com.picpay.desafio.android.remote.mapper.UsersMapper
import com.picpay.desafio.android.remote.source.RemoteDataSource
import com.picpay.desafio.android.repository.UsersRepository
import com.picpay.desafio.android.responses.ResultRemote
import com.picpay.desafio.android.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersRepositoryImpl(
    private val usersCacheDataSource: UsersCacheDataSource,
    private val remoteDataSource: RemoteDataSource
) : UsersRepository {

    private suspend fun getUsersRemote(): ResultRequired<List<UsersDomain>> {
        return when (val resultRemote = remoteDataSource.getUsers()) {
            is ResultRemote.Success -> {
                val mappedList = UsersMapper.map(resultRemote.response)

                val cacheList = UsersCacheMapper.mapJobsToCache(mappedList)

                usersCacheDataSource.updateData(cacheList)

                ResultRequired.Success(
                    result = mappedList
                )
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(resultRemote.throwable)
            }
        }
    }

    override fun getUsers(): Flow<ResultRequired<List<UsersDomain>>> {
        return usersCacheDataSource.getUsers()
            .map { cacheList ->
                val result = when {
                    cacheList.isEmpty() -> getUsersRemote()
                    else -> {
                        val jobs = UsersCacheMapper.map(cacheList)
                        ResultRequired.Success(jobs)
                    }
                }

                result
            }
    }
}