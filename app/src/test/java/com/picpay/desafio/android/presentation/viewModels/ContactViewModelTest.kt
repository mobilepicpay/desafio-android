package com.picpay.desafio.android.presentation.viewModels

import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.extensions.orFalse
import com.picpay.desafio.android.providers.MockContactProvider
import com.picpay.desafio.android.providers.MockErrorProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test

class ContactViewModelTest : BaseTest() {

    private lateinit var viewModel: ContactViewModel
    private val listContactsUseCase = mockk<ListContactsUseCase>(relaxed = true)

    override fun setup() {
        super.setup()
        viewModel = ContactViewModel(listContactsUseCase)
    }

    @Test
    fun shouldLoadingContacts() {
        viewModel.run {
            coEvery { listContactsUseCase() } returns MockContactProvider.mockedFlowContacts()
            loadContacts()
            coVerify { listContactsUseCase() }
            assertTrue(contacts.value?.isNotEmpty().orFalse())
        }
    }

    @Test
    fun shouldNotLoadContacts() {
        viewModel.run {
            coEvery { listContactsUseCase() } returns MockErrorProvider.mockErrorFlow()
            loadContacts()
            coVerify { listContactsUseCase() }
            assertNotNull(messageResource.value)
        }
    }
}