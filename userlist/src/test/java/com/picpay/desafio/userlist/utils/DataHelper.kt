package com.picpay.desafio.userlist.utils

import com.google.gson.Gson
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.common.utils.JsonReader
import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.security.InvalidParameterException
import kotlinx.coroutines.delay

object DataHelper {

    val listOfUsers =
        Gson().fromJson(JsonReader.readMockedJson("userlist.json"), Array<User>::class.java)
            .toList()
    val responseEmpty = Response.success<List<User>>(null)

    val responseSuccess = Response.success(listOfUsers)

    val responseError = Response.error<List<User>>(
        500,
        "\"message\": \"\"".toResponseBody(
            null
        )
    )

    val flowResponseSuccess = flow {
        emit(Resource.success(listOfUsers))
    }
    val flowResponseError = flow {
        emit(Resource.error<List<User>>(InvalidParameterException()))
    }
    val flowResponseEmpty = flow {
        emit(Resource.empty<List<User>>())
    }
}