package com.picpay.desafio.android.presentation.userlist

import com.google.common.truth.Truth
import com.picpay.desafio.android.core_network.models.NetworkError
import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.domain.usecase.UsersInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UserListViewModelTest {

    private lateinit var viewmodel: UserListViewModel
    private val interactor: UsersInteractor = mockk()

    private fun setUp() {
        viewmodel = UserListViewModel(interactor, UnconfinedTestDispatcher())
    }

    @Test
    fun `Should call user interactor on start`() = runBlocking {
        coEvery { interactor.getUsers() } returns flow {
            emit(Response.Success(listOf()))
        }
        setUp()

        coVerify(exactly = 1) { interactor.getUsers() }
        confirmVerified(interactor)
    }

    @Test
    fun `Should set screen state to success when get user succeeds`() = runBlocking<Unit> {
        coEvery { interactor.getUsers() } returns flow {
            emit(Response.Success(listOf()))
        }
        setUp()

        val state = viewmodel.screenState.first()

        Truth.assertThat(state).isInstanceOf(UserListState.Ready::class.java)
    }


    @Test
    fun `Should set screen state to error when get user fails`() = runBlocking {
        coEvery { interactor.getUsers() } returns flow {
            emit(Response.Error(NetworkError("", 404)))
        }
        setUp()

        val state = viewmodel.screenState.first()

        Truth.assertThat(state).isInstanceOf(UserListState.Error::class.java)
    }

    @Test
    fun `Should call user interactor on retry`() = runBlocking {
        coEvery { interactor.getUsers() } returns flow {
            emit(Response.Success(listOf()))
        }
        setUp()

        viewmodel.retryRequest()

        coVerify(exactly = 2) { interactor.getUsers() }
        confirmVerified(interactor)
    }

}