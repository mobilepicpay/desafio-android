package com.picpay.desafio.android.contacts.data.network

import com.picpay.desafio.android.contacts.data.model.ContactResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ContactsService {

    @GET("users")
    fun getContacts(): Single<Response<List<ContactResponse>>>
}