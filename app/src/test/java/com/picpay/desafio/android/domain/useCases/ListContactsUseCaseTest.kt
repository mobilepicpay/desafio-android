package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.domain.repository.ContactRepository
import com.picpay.desafio.android.providers.MockContactProvider
import com.picpay.desafio.android.providers.MockErrorProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
        coEvery { repository.getContacts() } returns MockContactProvider.mockedFlowContacts()
        listContactsUseCase()
        coVerify { repository.getContacts() }
    }

    @Test
    fun shouldNotListContacts() {
        coEvery { repository.getContacts() } returns MockErrorProvider.mockErrorFlow()
        listContactsUseCase()
        coVerify { repository.getContacts() }
    }

}