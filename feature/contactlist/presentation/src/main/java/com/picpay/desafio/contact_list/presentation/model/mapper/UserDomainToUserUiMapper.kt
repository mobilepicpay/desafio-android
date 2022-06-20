package com.picpay.desafio.contact_list.presentation.model.mapper

import com.picpay.desafio.contact_list.presentation.model.UserUi
import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class UserDomainToUserUiMapper {
    fun mapToUi(domain: UserDomain) = UserUi(
        img = domain.img,
        name = domain.name,
        id = domain.id,
        username = domain.username
    )
}