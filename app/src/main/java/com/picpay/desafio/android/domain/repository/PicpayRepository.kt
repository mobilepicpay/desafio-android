package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.domain.models.User
import kotlinx.coroutines.flow.Flow

internal interface PicpayRepository {

    suspend fun getUsers(): Flow<Response<List<User>>>

}