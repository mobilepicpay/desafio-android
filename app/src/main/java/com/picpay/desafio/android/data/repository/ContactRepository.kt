package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun getContacts(): Flow<List<ContactModel>>

}