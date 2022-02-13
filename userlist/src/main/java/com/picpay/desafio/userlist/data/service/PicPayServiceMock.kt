package com.picpay.desafio.userlist.data.service

import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.delay
import retrofit2.Response

class PicPayServiceMock: PicPayService {
    override suspend fun getUsers(): Response<List<User>> {
        delay(3000)
        return Response.success(listOf())
    }
}