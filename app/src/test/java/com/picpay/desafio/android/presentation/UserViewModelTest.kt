package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.desafio.picpay.android.testcoreutil.CoroutineRule
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.domain.FetchUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.UseCaseError
import com.picpay.desafio.android.ui.presentation.UserMapper
import com.picpay.desafio.android.ui.presentation.UserViewModel
import com.picpay.desafio.android.ui.presentation.ViewState

@RunWith(JUnit4::class)
class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var subject: UserViewModel

    private val useCase: FetchUseCase = mockk(relaxed = true)
    private val mapper: UserMapper = mockk(relaxed = true)

    @Before
    fun setUp() {
        subject = UserViewModel(mapper, useCase)
    }

    @Test
    fun `given a user list when the use case is called then should return success status`() {
        coEvery { useCase.execute() } coAnswers
                { mockUser() }

        subject.userState.observeForever { }
        subject.fetchUsers()

        Assert.assertTrue(subject.userState.value is ViewState.Success)
    }

    @Test
    fun `given an unexpected error when the use case is called should return error status`() {
        coEvery { useCase.execute() } coAnswers
                { Result.Error(UseCaseError(""))}

        subject.userState.observeForever { }
        subject.fetchUsers()

        Assert.assertTrue(subject.userState.value is ViewState.Error)
    }

    private fun mockUser() =
        Result.Success(listOf(User(id = 1, name = "Maria", username = "@M", img = "http://")))
}