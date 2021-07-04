package com.picpay.desafio.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.result.ResultWrapper
import com.picpay.desafio.android.domain.usecase.UserUseCase
import com.picpay.desafio.android.state.UserState
import com.picpay.desafio.android.util.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var stateObserver: Observer<UserState> = mockk(relaxed = true)
    private val mockUserUseCase: UserUseCase = mockk()
    private val viewModel: UserViewModel = UserViewModel(mockUserUseCase)

    @Before
    fun init() {
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `Given Request Get Users Success Should Return Success Object`() {
        //Given
        val mockResponse = mockk<List<User>>()
        coEvery { mockUserUseCase.getUsers(any()) } returns ResultWrapper.Success(mockResponse)

        //When
        viewModel.getUsers(true)

        //Then
        verifySequence {
            stateObserver.onChanged(UserState.ShowLoading(true))
            stateObserver.onChanged(UserState.ShowLoading(false))
            stateObserver.onChanged(UserState.ShowUserList(mockResponse))
        }
    }

    @Test
    fun `Given Request Get Users Error Should Return Error Object`() {
        //Given
        val mockError = "Error"
        coEvery { mockUserUseCase.getUsers(any()) } returns ResultWrapper.Error(mockError)

        //When
        viewModel.getUsers(true)

        //Then
        verifySequence {
            stateObserver.onChanged(UserState.ShowLoading(true))
            stateObserver.onChanged(UserState.ShowLoading(false))
            stateObserver.onChanged(UserState.Error(null))
        }
    }

    @Test
    fun `Given Request Get Users Force Update Without Internet And Without Data Saved in Database Should Return Error Message`() {
        //Given
        val error = "Erro ao atualizar remotamente, verifique sua conexão."

        //When
        viewModel.getUsers(false, forceUpdate = true)

        //Then
        verify {
            stateObserver.onChanged(UserState.Error(error))
        }
    }
}