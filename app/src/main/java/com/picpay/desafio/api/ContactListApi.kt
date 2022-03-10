package com.picpay.desafio.api

import com.picpay.desafio.models.ContactListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ContactListApi {

    @GET("users")
    suspend fun getContactList(): Response<ContactListResponse>
}