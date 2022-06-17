package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getContacts(): Flow<List<ContactModel>>
}