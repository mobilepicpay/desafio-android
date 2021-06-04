package com.picpay.desafio.android.remote.source

import com.picpay.desafio.android.remote.api.UserApi
import com.picpay.desafio.android.remote.extesion.mapRemoteErrors
import com.picpay.desafio.android.remote.model.UserData
import com.picpay.desafio.android.responses.ResultRemote

interface RemoteDataSource {
    suspend fun getUsers(): ResultRemote<List<UserData>>
}

class RemoteDataSourceImpl(
    private val userApi: UserApi
) : RemoteDataSource {

    override suspend fun getUsers(): ResultRemote<List<UserData>> {
        return try {
            val userPayload = userApi.getUsers()

            ResultRemote.Success(
                response = userPayload
            )
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }
}