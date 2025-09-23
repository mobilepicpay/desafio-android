package com.picpay.desafio.android.presentation.posts.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.domain.model.Post
import com.picpay.desafio.android.domain.usecase.GetPostsUseCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class PostsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val getPostsUseCase = mock(GetPostsUseCase::class.java)

    private lateinit var viewModel: PostsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PostsViewModel(getPostsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadPosts should emit Loading then Success when use case succeeds`() = runBlocking {
        // given
        val expectedPosts = listOf(
            Post(1, 1, "Test Title 1", "Test Body 1"),
            Post(2, 2, "Test Title 2", "Test Body 2")
        )
        `when`(getPostsUseCase()).thenReturn(expectedPosts)

        // when
        viewModel.loadPosts()

        // then
        assertTrue(viewModel.state.value is PostsState.Success)
        val successState = viewModel.state.value as PostsState.Success
        assertEquals(expectedPosts, successState.posts)
    }

    @Test
    fun `loadPosts should emit Loading then Error when use case fails`() = runBlocking {
        // given
        val expectedError = RuntimeException("Network error")
        `when`(getPostsUseCase()).thenThrow(expectedError)

        // when
        viewModel.loadPosts()

        // then
        assertTrue(viewModel.state.value is PostsState.Error)
        val errorState = viewModel.state.value as PostsState.Error
        assertEquals("Network error", errorState.message)
    }

    @Test
    fun `initial state should be Loading when viewModel is created`() {
        // then
        assertTrue(viewModel.state.value is PostsState.Loading)
    }
}
