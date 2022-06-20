package com.picpay.desafio.contactlist.datasource.remote.impl

import com.picpay.desafio.feature.contactlist.repository.UserRemoteDataSource
import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class UserRemoteDataSourceImpl(
    private val userService: UserService,
    private val mapper: UserResponseToUserDomainMapper
) : UserRemoteDataSource {
    override suspend fun getUsers(): List<UserDomain> {
        return userService.getUsers().map {
            mapper.mapToDomain(it)
        }
    }
}