package com.picpay.desafio.android.users.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.common.LoadState
import com.picpay.desafio.android.common.LoadState.SUCCESS
import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.repo.UsersApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class UsersListViewModelTest {

    private val api = mock<UsersApi>()

    private val myObserver = mock<Observer<LoadState>>()

    @get:Rule
    val testCoroutineRule = InstantTaskExecutorRule()

    @Test
    fun checkStartsReady() {
        val viewModel = UsersListViewModel(api, Dispatchers.Unconfined)
        assertEquals(LoadState.READY, viewModel.usersListState.value)
    }

    @Test
    fun checkSuccess() {
        runBlocking {
            val expectedUsers = emptyList<UserResponse>()

            // when
            whenever(api.getUsers()).thenReturn(Response.success(expectedUsers))

            //then
            val viewModel = UsersListViewModel(api, Dispatchers.Unconfined).apply {
                usersListState.observeForever(myObserver)
            }
            viewModel.loadUsers()

            assert(viewModel.usersListState.value is SUCCESS)
        }
    }

    @Test
    fun checkError() {
        runBlocking {
            // when
            whenever(api.getUsers()).thenReturn(Response.error(400, errorResponse))
            //then
            val viewModel = UsersListViewModel(api, Dispatchers.Unconfined).apply {
                usersListState.observeForever(myObserver)
            }
            viewModel.loadUsers()

            assertEquals(LoadState.ERROR, viewModel.usersListState.value)
        }
    }

    companion object {
        val bufferedSource = mock<BufferedSource>()

        val errorResponse = object: ResponseBody() {
            override fun contentLength(): Long = 0L

            override fun contentType(): MediaType? = null

            override fun source(): BufferedSource = bufferedSource
        }
    }
}