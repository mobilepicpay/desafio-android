package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.Contact

interface ContactRepository {

    suspend fun getContacts(): Result<List<Contact>>
}