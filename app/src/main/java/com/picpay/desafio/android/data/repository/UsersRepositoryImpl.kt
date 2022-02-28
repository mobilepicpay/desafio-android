package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.models.UserMapper
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.entities.UserEntity
import com.picpay.desafio.android.domain.repositories.UsersRepository

class UsersRepositoryImpl(
    private val remoteDatasource: UsersRemoteDatasource,
    private val localDatasource: UsersLocalDatasource
) : UsersRepository {
    override suspend fun getRemoteUsers(): Result<List<UserEntity>> {
        val response = remoteDatasource.getUsers()
        return if (response.isSuccessful) {
            Result.Success(UserMapper.mapToDomain(response.body()!!))
        } else {
            Result.Error(Exception("Falha ao recuperar lista de usuarios"))
        }
    }

    override suspend fun getCachedUsers(): Result<List<UserEntity>> {
        TODO("Not yet implemented")
    }
}