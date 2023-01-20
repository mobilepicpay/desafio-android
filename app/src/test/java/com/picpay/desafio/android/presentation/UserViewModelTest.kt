package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.UsersStub.listUsers
import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.usecase.GetAndUpdateUsersUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val getUsersUseCase = mockk<GetUsersUseCase>(relaxed = true)
    private val getAndUpdateUsersUseCase = mockk<GetAndUpdateUsersUseCase>(relaxed = true)

    private var observer = mockk<Observer<UserViewState>>(relaxed = true)

    private lateinit var viewModel: UserViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserViewModel(getUsersUseCase, getAndUpdateUsersUseCase)
        viewModel.uiState.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given call getUser When return's empty list Than should emit Loading and Success`() =
        runTest {
            every { getUsersUseCase.invoke() } returns flow { emit(Outcome.Success(listOf())) }
            viewModel.getUsers()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listOf())) }
        }

    @Test
    fun `Given call getUser When return's users list Than should emit Loading and Success`() =
        runTest {
            every { getUsersUseCase.invoke() } returns flow { emit(Outcome.Success(listUsers)) }
            viewModel.getUsers()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listUsers)) }
        }

    @Test
    fun `Given call getUser When return's multiple users list Than should emit Loading, Success and Success `() =
        runTest {
            every { getUsersUseCase.invoke() } returns flow {
                emit(Outcome.Success(listOf()))
                emit(Outcome.Success(listUsers))
            }
            viewModel.getUsers()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listOf())) }
            verify { observer.onChanged(UserViewState.Success(listUsers)) }
        }

    @Test
    fun `Given call getUser When occurred error Than should emit Loading and Error`() =
        runTest {
            val expectedError = DataError(0, "error")
            every { getUsersUseCase.invoke() } returns flow {
                emit(
                    Outcome.Error(DataError(0, "error"))
                )
            }
            viewModel.getUsers()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Error(expectedError)) }
        }

    @Test
    fun `Given call refresh When return's empty list Than should emit Loading and Success`() =
        runTest {
            every { getAndUpdateUsersUseCase.invoke() } returns flow { emit(Outcome.Success(listOf())) }
            viewModel.refresh()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listOf())) }
        }

    @Test
    fun `Given call refresh When return's users list Than should emit Loading and Success`() =
        runTest {
            every { getAndUpdateUsersUseCase.invoke() } returns flow { emit(Outcome.Success(listUsers)) }
            viewModel.refresh()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listUsers)) }
        }

    @Test
    fun `Given call refresh When occurred error Than should emit Loading and Error`() =
        runTest {
            val expectedError = DataError(0, "error")
            every { getAndUpdateUsersUseCase.invoke() } returns flow {
                emit(
                    Outcome.Error(DataError(0, "error"))
                )
            }
            viewModel.refresh()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Error(expectedError)) }
        }
}
