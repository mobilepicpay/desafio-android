package com.picpay.desafio.android.ui.viewModel

import app.cash.turbine.test
import com.picpay.desafio.android.domain.model.UiState
import com.picpay.desafio.android.repository.FakeUserRepository
import com.picpay.desafio.android.ui.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertIs
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class UserViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: FakeUserRepository
    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeUserRepository()
        viewModel = UserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Loading`() = runTest {
        viewModel.uiState.test {
            //estado da viewModel sempre deve ser loading
            assertIs<UiState.Loading>(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
    @Test
    fun `loadUsers should emit Success with users`() = runTest {
        viewModel.uiState.test {
            assertIs<UiState.Loading>(awaitItem())

            viewModel.loadUsers()
            advanceUntilIdle()

            val successState = awaitItem() as UiState.Success
            assertEquals(2, successState.users.size)
            assertEquals("User 1", successState.users[0].name)
        }
    }
    @Test
    fun `refreshUsers should emit Error when repository fails`() = runTest {
        //erro proposital no repositorio
        repository.shouldReturnError = true

        viewModel.uiState.test {
            assertIs<UiState.Loading>(awaitItem())

            viewModel.refreshUsers()
            advanceUntilIdle()

            val errorState = awaitItem() as UiState.Error
            assertEquals("Erro simulado no refresh", errorState.message)
        }
    }

    @Test
    fun `should handle empty user list`() = runTest {
        repository.mockUsers = emptyList()

        //estado success emitido mesmo com lista vazia
        viewModel.uiState.test {
            assertIs<UiState.Loading>(awaitItem())

            viewModel.loadUsers()
            advanceUntilIdle()

            val successState = awaitItem() as UiState.Success
            assertTrue(successState.users.isEmpty())
        }
    }

    @Test
    fun `should handle concurrent requests`() = runTest {
        //lida com a concorrencia na viewModel
        repository.delayInMillis = 500

        viewModel.uiState.test {
            assertIs<UiState.Loading>(awaitItem())

            viewModel.loadUsers()
            viewModel.refreshUsers()

            advanceUntilIdle()

            val lastState = awaitItem()
            assertTrue(lastState is UiState.Success || lastState is UiState.Error)
        }
    }
}