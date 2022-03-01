package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.mapper.UserMapper
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.entities.UserEntity
import com.picpay.desafio.android.domain.repositories.UsersRepository

class UsersRepositoryImpl(
    private val remoteDatasource: UsersRemoteDatasource,
    private val localDatasource: UsersLocalDatasource
) : UsersRepository {

    override suspend fun getRemoteUsers(): Result<List<UserEntity>> {
        return try {
            val response = remoteDatasource.getUsers()
            return if (response.isSuccessful) {
                val users = response.body()!!
                localDatasource.saveUsers(users)
                Result.Success(UserMapper.mapResponseToDomain(users))
            } else {
                Result.Error(Exception("Falha ao recuperar lista de usuarios"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getCachedUsers(): Result<List<UserEntity>> {
        return try {
            val response = localDatasource.getUsers()
            Result.Success(UserMapper.mapResponseToDomain(response))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}