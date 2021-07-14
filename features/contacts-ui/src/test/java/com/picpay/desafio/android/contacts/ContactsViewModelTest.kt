package com.picpay.desafio.android.contacts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.shared.model.ResultWrapper
import com.picpay.desafio.android.shared.model.ViewUser
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Vitor Herrmann on 13/07/2021
 */

@ExperimentalCoroutinesApi
class ContactsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val interactor = mockk<ContactsInteractor>()
    private lateinit var viewModel: ContactsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ContactsViewModel(interactor)
    }

    @Test
    fun `fetch contacts when repository is empty and save them`() = runBlockingTest {
        coEvery { interactor.fetchContacts() } returns ResultWrapper.Success(listOf())
        coEvery { interactor.getContacts() } returns listOf()
        coEvery { interactor.saveContacts(any()) } just runs

        viewModel.fetchContacts()

        Assert.assertEquals(Unit, viewModel.onShowLoading.value)
        Assert.assertEquals(Unit, viewModel.onHideLoading.value)
        Assert.assertEquals(listOf<ViewUser>(), viewModel.onFetchContactsSuccess.value)
        Assert.assertNull(viewModel.onError.value)

        coVerify(exactly = 1) {
            interactor.fetchContacts()
            interactor.getContacts()
            interactor.saveContacts(listOf())
        }

        confirmVerified(interactor)
    }

    @Test
    fun `call onError when unsuccessfully trying to fetch contacts`() = runBlockingTest {
        val error = Throwable()

        coEvery { interactor.fetchContacts() } returns ResultWrapper.Error(error)
        coEvery { interactor.getContacts() } returns listOf()

        viewModel.fetchContacts()

        Assert.assertEquals(Unit, viewModel.onShowLoading.value)
        Assert.assertEquals(Unit, viewModel.onHideLoading.value)
        Assert.assertNull(viewModel.onFetchContactsSuccess.value)
        Assert.assertEquals(error, viewModel.onError.value)

        coVerify(exactly = 1) {
            interactor.fetchContacts()
            interactor.getContacts()
        }

        confirmVerified(interactor)
    }

    @Test
    fun `just fill contacts when repository is not empty`() = runBlockingTest {
        val expected = listOf(
            ViewUser("", "", ""),
            ViewUser("", "", "")
        )

        coEvery { interactor.getContacts() } returns expected

        viewModel.fetchContacts()

        Assert.assertNull(viewModel.onShowLoading.value)
        Assert.assertNull(viewModel.onHideLoading.value)
        Assert.assertEquals(expected, viewModel.onFetchContactsSuccess.value)
        Assert.assertNull(viewModel.onError.value)

        coVerify(exactly = 1) { interactor.getContacts() }

        confirmVerified(interactor)
    }
}
