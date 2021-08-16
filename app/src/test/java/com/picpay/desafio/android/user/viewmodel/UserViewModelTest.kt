package com.picpay.desafio.android.user.viewmodel

import com.picpay.desafio.android.helper.rule.CoroutineViewModelTest
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class UserViewModelTest : CoroutineViewModelTest() {

    private val repository = mockk<UserRepository>()
    private val viewModel = UserViewModel(repository)

    @Test
    fun `Should post users list and loading false when repository succeeds`() {
        val users = listOf(
            UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username")
        )
        coEvery { repository.getUsers() } returns flowOf(users)

        viewModel.getUsers()

        viewModel.state.observeForever {
            assertEquals(it.users, users)
            assertFalse(it.isLoading)
            assertNull(it.error)
        }
    }

    @Test
    fun `Should post loading as true when get users is called`() {
        coEvery { repository.getUsers() } returns flowOf(emptyList())

        mainTestDispatcher.pauseDispatcher()

        viewModel.getUsers()

        viewModel.state.observeForever {
            assertTrue(it.isLoading)
        }

        mainTestDispatcher.resumeDispatcher()
    }

    @Test
    fun `Should post error with correct message when get users throws exception`() = runBlocking {
        val errorFlow = flow<List<UserDomain>> {
            throw Throwable("error")
        }

        coEvery { repository.getUsers() } returns errorFlow

        viewModel.getUsers()

        viewModel.state.observeForever {
            assertEquals("error", it.error)
            assertFalse(it.isLoading)
            assertNull(it.users)
        }
    }
}