package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun getContacts(): Flow<List<UserModel>>

}