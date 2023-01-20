package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.usecase.GetAndUpdateUsersUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class UserViewModelTest {

    private val getUsersUseCase: GetUsersUseCase = mockk()
    private val getUpdateUseCase: GetAndUpdateUsersUseCase = mockk()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private val stateObserver: Observer<UserViewState> = mockk(relaxed = true)
    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        viewModel = UserViewModel(getUsersUseCase, getUpdateUseCase)

    }

    @Test
    fun seila() = runTest {
        viewModel.uiState.observeForever(stateObserver)
        val loading = UserViewState.Loading
        coEvery { getUsersUseCase.invoke() } returns flow { emit(Outcome.Success(listOf())) }
        coEvery { getUpdateUseCase.invoke() } returns flow { emit(Outcome.Success(listOf())) }

        viewModel.getUsers()
        verify(stateObserver).onChanged(loading)
//        verify(stateObserver).onChanged(UserViewState.Success(listOf()))
    }
}
