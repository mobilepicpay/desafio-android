package com.picpay.desafio.android.contacts.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.commons.base.SchedulerProvider
import com.picpay.desafio.android.commons.mapper.Mapper
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.data.network.ContactsService
import com.picpay.desafio.android.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.contacts.data.repository.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.domain.mapper.ContactResponseMapper
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetContacts
import com.picpay.desafio.android.contacts.domain.usecase.GetContactsImpl
import com.picpay.desafio.android.contacts.presentation.viewmodel.ContactsViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.lang.reflect.Type

@RunWith(AndroidJUnit4ClassRunner::class)
class ContactsActivityTest : KoinTest {


    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Mock
    lateinit var service: ContactsService

    val mockModule = module {

        viewModel { ContactsViewModel(get(), get()) }

        single { service }

        single<ContactsRepository> {
            ContactsRepositoryImpl(service = get())
        }

        single<GetContacts> {
            GetContactsImpl(mapper = get(), repository = get())
        }

        single<Mapper<ContactResponse, Contact>> { ContactResponseMapper() }

        single<SchedulerProvider> {
            object : SchedulerProvider {
                override val ui: Scheduler get() = Schedulers.trampoline()
                override val io: Scheduler get() = Schedulers.trampoline()
                override val computation: Scheduler get() = Schedulers.trampoline()
            }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(mockModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun shouldDisplayTitle() {
        Mockito
            .`when`(service.getContacts())
            .thenReturn(Single.just(Response.success(emptyList())))

        launchActivity<ContactsActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle))
                .check(matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        val type: Type = object : TypeToken<List<ContactResponse>>() {}.type
        val response = Gson().fromJson<List<ContactResponse>>(body, type)
        val result = Single.just(Response.success(response))

        Mockito
            .`when`(service.getContacts())
            .thenReturn(result)

        launchActivity<ContactsActivity>().apply {
            onView(withText("@eduardo.santos"))
                .check(matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun onErrorShouldShowEmptyRecyclerView() {
        Mockito
            .`when`(service.getContacts())
            .thenReturn(Single.error(Throwable()))

        launchActivity<ContactsActivity>().apply {
            onView(withId(R.id.contactContainer))
                .check(doesNotExist())
        }
    }

    companion object {
        val body =
            "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"
    }
}