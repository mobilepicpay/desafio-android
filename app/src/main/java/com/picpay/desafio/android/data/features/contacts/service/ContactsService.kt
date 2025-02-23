package com.picpay.desafio.android.data.features.contacts.service

import com.picpay.desafio.android.data.features.contacts.model.ContactRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactsService {
    @GET("{endpoint}")
    suspend fun getContacts(
        @Path("endpoint")
        endpoint: String
    ): List<ContactRemote>
}