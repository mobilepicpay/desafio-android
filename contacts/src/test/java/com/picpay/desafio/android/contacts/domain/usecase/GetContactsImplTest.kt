package com.picpay.desafio.android.contacts.domain.usecase

import com.picpay.desafio.android.commons.mapper.Mapper
import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.contacts.domain.mapper.ContactResponseMapper
import com.picpay.desafio.android.contacts.domain.model.Contact
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetContactsImplTest {
    @MockK
    lateinit var repository: ContactsRepository

    lateinit var mapper: Mapper<ContactResponse, Contact>

    lateinit var useCase: GetContacts

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = ContactResponseMapper()
        useCase = GetContactsImpl(mapper, repository)
    }

    @Test
    fun test() {
        val mock = listOf(
            ContactResponse("img", "name", 0, "username"),
            ContactResponse("img1", "name1", 1, "username1"),
            ContactResponse("img2", "name2", 2, "username2")
        )
        val expected = listOf(
            Contact("img", "name", 0, "username"),
            Contact("img1", "name1", 1, "username1"),
            Contact("img2", "name2", 2, "username2")
        )
        every { repository.getContacts() } returns Single.just(mock)
        useCase
            .execute()
            .test()
            .assertValue {
                it == expected
            }
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun testError() {
        val mock = Throwable("error")
        every { repository.getContacts() } returns Single.error(mock)

        useCase
            .execute()
            .test()
            .assertError { it.message == "error" }
    }
}