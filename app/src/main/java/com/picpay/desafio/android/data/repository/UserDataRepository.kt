package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.mapper.toListModel
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataRepository(private val remoteDataSource: UserRemoteDataSource) : UserRepository {

    override fun getUsers(): Flow<List<UserModel>> = remoteDataSource.getUsers().map { it.toListModel() }
}