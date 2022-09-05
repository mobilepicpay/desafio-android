package com.picpay.desafio.android.presentation.viewModel

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.provider.MockErrorProvider
import com.picpay.desafio.android.provider.MockUserProvider
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
            coEvery { listContactsUseCase.execute() } returns MockUserProvider.mockedFlowUsers()
            loadContacts()
            coVerify { listContactsUseCase.execute() }
            val r = items.value
            assertEquals(MockUserProvider.mockedUsers(), r)
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