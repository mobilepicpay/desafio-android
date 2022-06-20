package com.picpay.desafio.feature.contactlist.repository

import com.picpay.desafio.feature.contactlist.usecase.UserDomain

interface UserRemoteDataSource {
    suspend fun getUsers(): List<UserDomain>
}

interface UserInternalDataSource {
    suspend fun getUsers(): List<UserDomain>
    suspend fun insertUsers(users: List<UserDomain>)
}