package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.remote.mapper.UserMapper

class UserRemoteDataSourceImpl(
    private val service: PicPayService,
    private val userMapper: UserMapper
) : UserRemoteDataSource {

    override suspend fun getUserData(): List<User> {
        var responseBody: List<User> = emptyList()
        val response = service.getUsers()
        if (response.code() == 200) {
            responseBody = response.body()?.map {
                userMapper.userResponse(it)
            }!!
        }
        return responseBody
    }

}