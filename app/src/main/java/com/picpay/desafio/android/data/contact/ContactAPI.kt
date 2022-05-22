package com.picpay.desafio.android.data.contact

import com.picpay.desafio.android.data.contact.model.ContactDTO
import retrofit2.http.GET

interface ContactAPI {

    @GET("users")
    suspend fun getContacts(): List<ContactDTO>
}