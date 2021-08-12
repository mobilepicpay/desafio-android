package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.utils.sealeds.DataConsumptionStatus
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<DataConsumptionStatus<List<User>, DataErrorException>>
    suspend fun refreshUsers(): Flow<DataConsumptionStatus<List<User>, DataErrorException>>
}
