package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.http.GET

internal interface PicpayApi {

    // This Endpoint is not a subdomain of the base url??
    @GET("")
    suspend fun getUsers(): List<UserResponse>

}