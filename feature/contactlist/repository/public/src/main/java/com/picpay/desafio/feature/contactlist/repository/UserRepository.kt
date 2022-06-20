package com.picpay.desafio.feature.contactlist.repository

import com.picpay.desafio.feature.contactlist.usecase.UserDomain

interface UserRepository {
    suspend fun getUsers(): Result<List<UserDomain>>
    suspend fun insertUsers(users: List<UserDomain>): Result<Unit>
}