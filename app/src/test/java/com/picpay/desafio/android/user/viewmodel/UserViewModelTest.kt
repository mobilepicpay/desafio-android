package com.picpay.desafio.android.user.viewmodel

import com.picpay.desafio.android.helper.CoroutineViewModelTest
import com.picpay.desafio.android.network.ResultWrapper
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class UserViewModelTest: CoroutineViewModelTest() {

    private val repository = mockk<UserRepository>()
    private val viewModel = UserViewModel(repository)

    @Test
    fun `Should post users list and loading false when repository succeeds`() {
        val users = listOf(
            UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username")
        )
        coEvery { repository.getUsers() } returns ResultWrapper.Success(users)

        viewModel.getUsers()

        viewModel.state.observeForever {
            assertEquals(it.users, users)
            assertFalse(it.isLoading)
        }
    }

    @Test
    fun `Should post loading as true when get users is called`() {
        coEvery { repository.getUsers() } returns ResultWrapper.Success(emptyList())

        mainTestDispatcher.pauseDispatcher()

        viewModel.getUsers()

        viewModel.state.observeForever {
            assertTrue(it.isLoading)
        }

        mainTestDispatcher.resumeDispatcher()
    }
}