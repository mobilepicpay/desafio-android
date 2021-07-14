package com.picpay.desafio.android.shared.usecases

import com.picpay.desafio.android.shared.database.ContactsDao
import com.picpay.desafio.android.shared.model.ViewUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Vitor Herrmann on 13/07/2021
 */
@ExperimentalCoroutinesApi
class GetContactsUseCaseTest {

    private lateinit var useCase: GetContactsUseCase
    private val contactsDao = mockk<ContactsDao>()

    @Before
    fun setup() {
        useCase = GetContactsUseCaseImpl(contactsDao)
    }

    @Test
    fun `get contacts successfully`() = runBlockingTest {
        coEvery { contactsDao.getAll() } returns listOf()

        val actual = useCase.getContacts()

        Assert.assertEquals(listOf<ViewUser>(), actual)

        coVerify(exactly = 1) { contactsDao.getAll() }

        confirmVerified(contactsDao)
    }
}
