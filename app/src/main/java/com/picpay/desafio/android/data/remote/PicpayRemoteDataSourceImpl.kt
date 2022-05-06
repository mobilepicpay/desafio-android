package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.model.UserResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class PicpayRemoteDataSourceImpl @Inject constructor(
    private val api: PicpayApi
) : PicpayRemoteDatasource {

    override suspend fun getUsers(): Flow<List<UserResponse>> {
        TODO("Not yet implemented")
    }

}