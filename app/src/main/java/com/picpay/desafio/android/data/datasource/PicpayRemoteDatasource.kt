package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.data.model.UserResponse
import kotlinx.coroutines.flow.Flow

internal interface PicpayRemoteDatasource {

    suspend fun getUsers(): Flow<Response<List<UserResponse>>>

}
