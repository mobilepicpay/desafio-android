package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.mapper.toListContactModel
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.repository.ContactRepository
import kotlinx.coroutines.flow.map

class ContactDataRepository(
    private val remoteDataSource: UserRemoteDataSource
) : ContactRepository {

    override fun getContacts() = remoteDataSource.getUsers().map { it.toListContactModel() }
}