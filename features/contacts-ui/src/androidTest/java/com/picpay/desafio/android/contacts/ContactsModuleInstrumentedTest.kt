package com.picpay.desafio.android.contacts

import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.contacts.adapter.UserListAdapter
import com.picpay.desafio.android.shared.model.ViewUser
import com.picpay.desafio.android.shared.usecases.FetchContactsUseCase
import com.picpay.desafio.android.shared.usecases.GetContactsUseCase
import com.picpay.desafio.android.shared.usecases.SaveContactsUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * @author Vitor Herrmann on 13/07/2021
 */

@ExperimentalCoroutinesApi
open class ContactsModuleInstrumentedTest {

    private lateinit var contactsInteractor: ContactsInteractor
    private lateinit var fetchContactsUseCase: FetchContactsUseCase
    private lateinit var getContactsUseCase: GetContactsUseCase
    private lateinit var saveContactsUseCaseImpl: SaveContactsUseCaseImpl

    protected fun loadTestModules() {
        contactsInteractor = mockk(relaxed = true)
        fetchContactsUseCase = mockk(relaxed = true)
        getContactsUseCase = mockk(relaxed = true)
        saveContactsUseCaseImpl = mockk(relaxed = true)

        coEvery { contactsInteractor.getContacts() } returns listOf(
            ViewUser("https://randomuser.me/api/portraits/men/1.jpg", "Sandrine Spinka", "Tod86"),
            ViewUser(
                "https://randomuser.me/api/portraits/men/2.jpg",
                "Carli Carroll",
                "Constantin_Sawayn"
            )
        )

        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            loadKoinModules(module {
                factory { UserListAdapter() }
                factory { fetchContactsUseCase }
                factory { getContactsUseCase }
                factory { saveContactsUseCaseImpl }
                factory { contactsInteractor }
                viewModel { ContactsViewModel(get()) }
            })
        }
    }
}
