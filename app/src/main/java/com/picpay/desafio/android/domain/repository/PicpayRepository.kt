package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.models.User
import kotlinx.coroutines.flow.Flow

internal interface PicpayRepository {

    suspend fun getUsers(): Flow<List<User>>

}