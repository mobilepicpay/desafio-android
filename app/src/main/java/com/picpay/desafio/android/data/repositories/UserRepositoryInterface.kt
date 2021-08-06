package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.data.utils.Resource
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    fun getUsers(isRefresh: Boolean): Flow<Resource<List<User>>>
}