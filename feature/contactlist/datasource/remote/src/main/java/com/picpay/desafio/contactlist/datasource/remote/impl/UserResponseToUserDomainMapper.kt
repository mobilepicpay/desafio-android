package com.picpay.desafio.contactlist.datasource.remote.impl

import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class UserResponseToUserDomainMapper {
    fun mapToDomain(response: UserResponse) =
        UserDomain(
            img = response.img,
            name = response.name,
            id = response.id,
            username = response.username
        )
}