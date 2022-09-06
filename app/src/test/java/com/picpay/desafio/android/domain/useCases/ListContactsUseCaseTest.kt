package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.data.repository.ContactRepository
import com.picpay.desafio.android.provider.MockContactProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ListContactsUseCaseTest : BaseTest() {
    private val repository = mockk<ContactRepository>(relaxed = true)

    private lateinit var listContactsUseCase: ListContactsUseCase

    override fun setup() {
        super.setup()
        listContactsUseCase = ListContactsUseCaseImpl(repository)
    }

    @Test
    fun shouldListContacts() {
        runBlocking {
            coEvery {
                repository.getContacts()
            } returns MockContactProvider.mockedFlowContacts()

            listContactsUseCase.execute().collect {
                assertEquals(MockContactProvider.mockedContacts(), it)
            }
        }
    }
}