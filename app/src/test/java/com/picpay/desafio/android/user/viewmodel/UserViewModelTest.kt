package com.picpay.desafio.android.user.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.user.CoroutineTestRule
import com.picpay.desafio.android.user.listContactsNotSuccess
import com.picpay.desafio.android.user.listContactsSuccess
import com.picpay.desafio.android.user.mockListUser
import com.picpay.desafio.android.user.usecase.UserUseCase
import com.picpay.desafio.android.user.viewmodel.state.UserState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.stubbing.answers.ThrowsException

class UserViewModelTest {
    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val scope: CoroutineTestRule = CoroutineTestRule()

    private val mockUserUseCase = mock<UserUseCase>()
    private val viewModel = UserViewModel(mockUserUseCase)

    @Test
    fun `Ao chamar metodo getContacts() com resposta de sucesso, deve retornar State de Successo`() {
        runBlocking {
            whenever(mockUserUseCase.getContacts()).thenReturn(listContactsSuccess)

            // when
            viewModel.getContacts()

            // then
            assertEquals(UserState.SuccessApi(mockListUser), viewModel.viewState.value)
        }
    }

    @Test
    fun `Ao chamar metodo getContacts() com resposta de erro, deve retornar State de Erro`() {
        runBlocking {
            whenever(mockUserUseCase.getContacts()).thenReturn(listContactsNotSuccess)

            // when
            viewModel.getContacts()

            // then
            assertEquals(
                UserState.ErrorApi("Não foi possível concluir sua solicitação no momento"),
                viewModel.viewState.value
            )
        }
    }
}