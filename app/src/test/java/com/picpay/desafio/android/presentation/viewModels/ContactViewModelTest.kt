package com.picpay.desafio.android.presentation.viewModels

import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.providers.ContactMockProvider
import com.picpay.desafio.android.providers.ErrorMockProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test

class ContactViewModelTest : BaseTest() {

    private lateinit var viewModel: ContactViewModel
    private val listContacts = mockk<ListContactsUseCase>(relaxed = true)

    override fun setup() {
        super.setup()
        viewModel = ContactViewModel(listContacts)
    }

    @Test
    fun loadContactsTest() {
        viewModel.run {
            coEvery { listContacts() } returns ContactMockProvider.mockedFlowContacts()
            loadContacts()
            coVerify { listContacts() }
            assert(contacts.value?.isNotEmpty() ?: false)
        }
    }

    @Test
    fun loadContactsTestError() {
        viewModel.run {
            coEvery { listContacts() } returns ErrorMockProvider.mockErrorFlow()
            loadContacts()
            coVerify { listContacts() }
            assert(messages.value != null)
        }
    }
}