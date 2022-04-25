package com.picpay.desafio.android.contacts.domain.mapper

import com.picpay.desafio.android.commons.mapper.Mapper
import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.domain.model.Contact

class ContactResponseMapper : Mapper<ContactResponse, Contact> {
    override fun transform(input: ContactResponse) = Contact(
        img = input.img,
        name = input.name,
        id = input.id,
        username = input.username
    )
}