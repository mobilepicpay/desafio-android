package com.picpay.desafio.android.shared.usecases

import com.picpay.desafio.android.shared.database.ContactsDao
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

/**
 * @author Vitor Herrmann on 13/07/2021
 */
@ExperimentalCoroutinesApi
class SaveContactsUseCaseTest {

    private lateinit var useCase: SaveContactsUseCase
    private val contactsDao = mockk<ContactsDao>()

    @Before
    fun setup() {
        useCase = SaveContactsUseCaseImpl(contactsDao)
    }

    @Test
    fun `save contacts successfully`() = runBlockingTest {
        coEvery { contactsDao.insertAll(any()) } just runs

        useCase.saveContacts(listOf())

        coVerify(exactly = 1) { contactsDao.insertAll(listOf()) }

        confirmVerified(contactsDao)
    }
}
