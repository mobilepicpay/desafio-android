package com.picpay.desafio.android.contacts.data.repository

import com.picpay.desafio.android.contacts.data.network.ContactsService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ContactsRepositoryImplTest {

    lateinit var repository: ContactsRepository

    @MockK
    lateinit var service: ContactsService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ContactsRepositoryImpl(service)
    }

    @Test
    fun testGetContacts() {
        every { service.getContacts() } returns Single.just(Response.success(listOf()))

        repository
            .getContacts()
            .test()
            .assertValue { it.isEmpty() }
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun testEmitError() {
        every {
            service.getContacts()
        } returns Single.just(
            Response.error(
                404,
                mockk {
                    every { contentType() } returns "application/json".toMediaType()
                    every { contentLength() } returns 0
                }
            )
        )

        repository
            .getContacts()
            .test()
            .assertError {
                it is NullPointerException
            }
    }
}