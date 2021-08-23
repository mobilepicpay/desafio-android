package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.di.*
import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.service.UserService
import com.picpay.desafio.android.util.Resource
import com.picpay.desafio.android.viewmodel.UserViewModel
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ExampleServiceTest : KoinTest {

    @Mock
    lateinit var observerData: MutableLiveData<Resource<List<UserDTO>>>

    val viewModel: UserViewModel by inject()

    @RelaxedMockK
    internal lateinit var repository: UserRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        startKoin() {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(networkModule,
                serviceModule,
                dataBase,
                repositoryModule,
                viewModelModule)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `get users success`() {
        val data = viewModel.loadList()
//        assertTrue(data.value is Resource.Loading)

        Mockito.verify(observerData).postValue(data.value)

//        coEvery {
//            val a =
//        } returns MutableLiveData(Resource.Success(a))
//
//        coVerify(exactly = 1) {
//            (viewModel.loadList() is Resource.Success)
//        }
    }
}