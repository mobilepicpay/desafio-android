package com.picpay.desafio.android.presentation.viewModel

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.provider.MockContactProvider
import com.picpay.desafio.android.provider.MockErrorProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class ContactViewModelTest : BaseTest() {

    private lateinit var contactListViewModel: ContactListViewModel
    private val listContactsUseCase = mockk<ListContactsUseCase>(relaxed = true)


    @Before
    override fun setup() {
        super.setup()
        contactListViewModel = ContactListViewModel(listContactsUseCase)
    }

    @Test
    fun shouldLoadingContacts() {
        contactListViewModel.run {
            coEvery { listContactsUseCase.execute() } returns MockContactProvider.mockedFlowContacts()
            loadContacts()
            coVerify { listContactsUseCase.execute() }
            val r = items.value
            assertEquals(MockContactProvider.mockedContacts(), r)

            assertTrue(responseState.value is State.Success)
        }
    }

    @Test
    fun shouldNotLoadContacts() {
        contactListViewModel.run {
            coEvery { listContactsUseCase.execute() } returns MockErrorProvider.mockErrorFlow()
            loadContacts()
            coVerify { listContactsUseCase.execute() }
            assert(responseState.value is State.Error)
        }
    }
}

val Boolean.Companion.FALSE: Boolean get() = false
fun Boolean?.orFalse() = this ?: Boolean.FALSE