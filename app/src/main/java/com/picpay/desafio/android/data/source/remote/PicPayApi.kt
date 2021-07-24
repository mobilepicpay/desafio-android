package com.picpay.desafio.android.data.source.remote

import com.picpay.desafio.android.data.dto.UserResponse
import retrofit2.http.GET

interface PicPayApi {

    @GET(GET_KEY_USERS)
    suspend fun getUsers(): List<UserResponse>


    companion object {
        const val GET_KEY_USERS = "users"
    }

}