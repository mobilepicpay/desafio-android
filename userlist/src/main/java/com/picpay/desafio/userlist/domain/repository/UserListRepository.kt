package com.picpay.desafio.userlist.domain.repository

import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    suspend fun getListFromServer(): Flow<Resource<List<User>>>
    suspend fun getListFromDatabase(): Flow<Resource<List<User>>>
    suspend fun saveListFromServer(newList:List<User>)
}