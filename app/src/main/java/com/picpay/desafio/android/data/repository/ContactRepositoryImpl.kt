package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.entity.toModel
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class ContactRepositoryImpl(
    private val api: PicPayService,
    private val dispatcher: CoroutineContext
) : ContactRepository {

    override suspend fun getContacts(): Flow<List<ContactModel>> = flow {
        try {
            val contactList = api.getUsers()
            emit(contactList.toModel())
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}