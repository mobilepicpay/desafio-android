package com.picpay.desafio.android.contacts.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
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
import com.picpay.desafio.android.contacts.domain.usecase.GetUsers
import com.picpay.desafio.android.contacts.domain.usecase.GetUsersImpl
import com.picpay.desafio.android.contacts.presentation.RecyclerViewMatchers.checkRecyclerViewItem
import com.picpay.desafio.android.contacts.presentation.viewmodel.ContactsViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Response
import java.lang.reflect.Type

@RunWith(AndroidJUnit4ClassRunner::class)
class ContactsActivityTest : KoinTest {


    private val server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    val mockModule = module {

        viewModel { ContactsViewModel(get(), get()) }

        single<ContactsService> {
            object : ContactsService {
                var type: Type = object : TypeToken<List<ContactResponse>>() {}.type
                val response = Gson().fromJson<List<ContactResponse>>(body, type)
                override fun getContacts(): Single<Response<List<ContactResponse>>> {
                    return Single.just(Response.success(response))
                }
            }
        }

        single<ContactsRepository> {
            ContactsRepositoryImpl(service = get())
        }

        single<GetUsers> {
            GetUsersImpl(mapper = get(), repository = get())
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
        launchActivity<ContactsActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle))
                .check(matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }

        server.start(serverPort)

        launchActivity<ContactsActivity>().apply {
            onView(withText("@eduardo.santos"))
                .check(matches(ViewMatchers.isDisplayed()))
        }

        server.close()
    }

    companion object {
        private const val serverPort = 8080
        val body =
            "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"
        private val successResponse by lazy {
            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}