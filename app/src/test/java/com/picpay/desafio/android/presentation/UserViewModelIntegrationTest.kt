package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.UsersStub.listUsers
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.usecase.GetAndUpdateUsersUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.fake.UserDaoFake
import com.picpay.desafio.android.fake.UserRemoteDataSourceFake
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class UserViewModelIntegrationTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val repository = UserRepositoryImpl(UserRemoteDataSourceFake(), UserDaoFake())
    private val getUsersUseCase = GetUsersUseCase(repository)
    private val getAndUpdateUsersUseCase = GetAndUpdateUsersUseCase(repository)

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
    fun `Given call getUser When return's multiple users list Than should emit Loading, Success and Success `() =
        runTest {
            viewModel.getUsers()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listUsers)) }
            verify { observer.onChanged(UserViewState.Success(listUsers)) }
        }

    @Test
    fun `Given call refresh When return's multiple users list Than should emit Loading, Success and Success `() =
        runTest {
            viewModel.refresh()
            runCurrent()

            verify { observer.onChanged(UserViewState.Loading) }
            verify { observer.onChanged(UserViewState.Success(listUsers)) }
        }
}
