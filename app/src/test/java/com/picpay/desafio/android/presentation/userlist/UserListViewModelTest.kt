package com.picpay.desafio.android.presentation.userlist

import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.domain.usecase.UsersInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UserListViewModelTest {

    private lateinit var viewmodel: UserListViewModel
    private val interactor: UsersInteractor = mockk()

    @Before
    fun setUp() {
        viewmodel = UserListViewModel(interactor, UnconfinedTestDispatcher())
    }

    @Test
    fun `Should call user interactor on start`() = runBlocking {
        coEvery { interactor.getUsers() } returns flow {
            emit(Response.Success(listOf()))
        }

        coVerify(exactly = 1) { interactor.getUsers() }
        confirmVerified(interactor)
    }

}