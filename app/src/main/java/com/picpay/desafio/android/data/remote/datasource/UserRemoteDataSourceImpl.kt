package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.remote.mapper.toUserModel

class UserRemoteDataSourceImpl(
    private val service: PicPayService
) : UserRemoteDataSource {

    override suspend fun getUser(): List<User> {
        val response = service.getUsers()
        return if (response.code() == 200) {
            response.body()!!.toUserModel()
        } else emptyList()
    }
}