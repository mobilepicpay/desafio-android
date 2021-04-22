package com.picpay.desafio.android.data_remote.api


import com.picpay.desafio.android.data_remote.models.RawUserResponse
import retrofit2.http.GET

interface PicPayUserService {

   @GET("users")
   suspend fun getUsers(): List<RawUserResponse>

    companion object {
        const val API_URL = "http://careers.picpay.com/tests/mobdev/"
    }
}