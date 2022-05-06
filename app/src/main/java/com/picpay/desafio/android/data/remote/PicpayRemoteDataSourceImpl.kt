package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.core_network.retrofit.client.RetrofitClient.makeCall
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class PicpayRemoteDataSourceImpl @Inject constructor(
    private val api: PicpayApi
) : PicpayRemoteDatasource {

    override suspend fun getUsers(): Flow<Response<List<UserResponse>>> = flow {
        makeCall { api.getUsers() }
    }

}
