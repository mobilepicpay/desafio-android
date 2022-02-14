package com.picpay.desafio.userlist.data.service

import com.google.gson.Gson
import com.picpay.desafio.common.utils.JsonReader
import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.delay
import retrofit2.Response
import java.security.InvalidParameterException

class PicPayServiceMock : PicPayService {
    override suspend fun getUsers(): Response<List<User>> {
        delay(2000)
        return Response.success(
            Gson().fromJson(
                JsonReader.readMockedJson("userlist.json"),
                Array<User>::class.java
            ).toList()
        )
    }
}