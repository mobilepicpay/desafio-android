package com.picpay.desafio.android.contacts.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.commons.base.SchedulerProvider
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetContacts
import com.picpay.desafio.android.contacts.presentation.ContactsViewState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class ContactsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var vm: ContactsViewModel

    @MockK
    lateinit var getContacts: GetContacts

    @MockK
    lateinit var schedulerProvider: SchedulerProvider

    @RelaxedMockK
    lateinit var stateObserver: Observer<ContactsViewState>

    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testScheduler = TestScheduler()
        every { schedulerProvider.io } returns testScheduler
        every { schedulerProvider.ui } returns testScheduler
    }

    @Test
    fun testSuccessStates() {
        val mock = listOf(Contact("img", "name", 0, "username"))
        every { getContacts.execute() } returns Single.just(mock)

        vm = ContactsViewModel(getContacts, schedulerProvider).apply {
            viewState.observeForever(stateObserver)
        }

        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)
        verify {
            stateObserver.onChanged(
                ContactsViewState(
                    contacts = emptyList(),
                    isLoading = true,
                    hasError = false
                )
            )
        }
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)
        verify {
            stateObserver.onChanged(
                ContactsViewState(
                    contacts = mock,
                    isLoading = false,
                    hasError = false
                )
            )
        }
    }

    @Test
    fun testErrorState() {
        val mock = Throwable("error")
        every { getContacts.execute() } returns Single.error(mock)

        vm = ContactsViewModel(getContacts, schedulerProvider).apply {
            viewState.observeForever(stateObserver)
        }

        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)
        verify {
            stateObserver.onChanged(
                ContactsViewState(
                    contacts = emptyList(),
                    isLoading = true,
                    hasError = false
                )
            )
        }
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)
        verify {
            stateObserver.onChanged(
                ContactsViewState(
                    contacts = emptyList(),
                    isLoading = false,
                    hasError = true
                )
            )
        }
    }
}