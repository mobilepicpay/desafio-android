package com.picpay.desafio.android.contacts.domain.mapper

import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.domain.model.Contact
import org.junit.Before
import org.junit.Test

class ContactResponseMapperTest {

    lateinit var mapper: ContactResponseMapper

    @Before
    fun setUp() {
        mapper = ContactResponseMapper()
    }

    @Test
    fun testTransform() {
        val input = ContactResponse(
            "img",
            "name",
            0,
            "username"
        )
        val output = Contact(
            "img",
            "name",
            0,
            "username"
        )
        assert(mapper.transform(input) == output)
    }
}