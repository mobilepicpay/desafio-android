package com.picpay.desafio.android.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.data.network.reponses.DataResponse
import com.picpay.desafio.android.data.network.reponses.StatusResponse
import com.picpay.desafio.android.data.repositories.UserRepository
import com.picpay.desafio.android.data.repositories.UserRepositoryInterface
import com.picpay.desafio.android.data.utils.Resource
import com.picpay.desafio.android.models.User
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.*

@ExperimentalCoroutinesApi
class UserViewModelTest : BaseTest(){

    @Mock
    private lateinit var repository: UserRepositoryInterface
    lateinit var viewModel: UserViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userObserver: Observer<DataResponse<List<User>>>

    @Captor
    private lateinit var userArgumentCaptor: ArgumentCaptor<DataResponse<List<User>>>

    private val testDispatcher = TestCoroutineDispatcher()

    override fun setUp() {
        super.setUp()
        Dispatchers.setMain(testDispatcher)
    }

    //region USER LIST
    @Test
    fun testUsersWhenIsSuccessShouldReturnList() = testDispatcher.runBlockingTest {
        val list = listOf(User(
            "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos", 1001,"@eduardo.santos"
        ), User(
            "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos 2", 1002,"@eduardo.santos2"
        ))

        Mockito.`when`(repository.getUsers(false)).thenReturn(
            flowOf(
                Resource.success(
                    list
                )
            )
        )

        viewModel = UserViewModel(repository)

        viewModel.users.apply {
            observeForever(userObserver)
        }

        Mockito.verify(userObserver, Mockito.times(1)).onChanged(
            userArgumentCaptor.capture()
        )

        val values = userArgumentCaptor.allValues
        assertEquals(1, values.size)
        assertEquals(StatusResponse.SUCCESS, values.first().status)
        assertEquals(list, values.first()?.data)
    }

    @Test
    fun testUserListWhenIsErrorShouldReturnFailure() = testDispatcher.runBlockingTest {
        Mockito.`when`(repository.getUsers(false)).thenReturn(
            flowOf(
                Resource.error("Error", null, null)
            )
        )
        viewModel = UserViewModel(repository)

        viewModel.users.apply {
            observeForever(userObserver)
        }

        Mockito.verify(userObserver, Mockito.times(1)).onChanged(
            userArgumentCaptor.capture()
        )

        val values = userArgumentCaptor.allValues
        assertEquals(1, values.size)
        assertEquals(StatusResponse.FAILURE, values.first().status)
    }

    //endregion

    @After
    fun teardown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}