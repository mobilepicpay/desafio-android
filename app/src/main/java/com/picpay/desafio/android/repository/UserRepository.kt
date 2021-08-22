package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers() : Flow<Resource<List<UserDTO>>>
}