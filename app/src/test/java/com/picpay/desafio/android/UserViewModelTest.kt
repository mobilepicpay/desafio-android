package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.presentation.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: UserViewModel
    private val repository: UserRepository = mock()
    private val usersObserver: Observer<List<User>> = mock()
    private val errorObserver: Observer<String> = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserViewModel(repository).apply {
            users.observeForever(usersObserver)
            error.observeForever(errorObserver)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenSuccessfulFetch_whenFetchUsersIsCalled_thenPostUsers() = runTest {
        val users = listOf(User(1, "url", "User Test", "usertest"))
        whenever(repository.getUsers()).thenReturn(users)

        viewModel.fetchUsers()
        advanceUntilIdle()

        verify(usersObserver).onChanged(users)
        verify(errorObserver, never()).onChanged(any())
    }

    @Test
    fun givenAPIFailure_whenFetchUsersIsCalled_thenPostErrorMessage() = runTest {
        whenever(repository.getUsers()).thenThrow(RuntimeException("API Error"))

        viewModel.fetchUsers()
        advanceUntilIdle()

        verify(errorObserver).onChanged("Erro ao carregar usuários")
    }
}
