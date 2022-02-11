package com.picpay.desafio.userlist.domain.repository

import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    suspend fun getUserList(): Flow<Resource<List<User>>>
}