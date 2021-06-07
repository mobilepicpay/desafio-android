package com.picpay.desafio.android.repository

import com.picpay.desafio.android.entities.UsersDomain
import com.picpay.desafio.android.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<ResultRequired<List<UsersDomain>>>
}
