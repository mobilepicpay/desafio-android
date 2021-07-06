package com.picpay.desafio.android.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.picpay.desafio.android.remote.model.User
import com.picpay.desafio.android.remote.repository.ApiListener
import com.picpay.desafio.android.remote.repository.DefaultRepository
import com.picpay.desafio.android.utls.Constants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var progressBarObserver: Observer<Int>

    @Mock
    private lateinit var recyclerViewObserver: Observer<Int>

    @Mock
    private lateinit var usersListObserver: Observer<List<User>>

    @Mock
    private lateinit var responseStatusObserver: Observer<Int>


    private lateinit var viewModel: MainViewModel

    @Test
    fun whenViewModelGetUsers_getSuccess_theListWillBeUpdated() {
        val mockResponseStatus = Constants.success
        val mockProgressBar = View.GONE
        val mockUsersList = listOf(
            User("testImage", "testName", 0, "testUsername"),
            User("testImage", "testName", 0, "testUsername"),
            User("testImage", "testName", 0, "testUsername")
        )

        val mockRepositorySuccess = MockRepositorySuccess(mockUsersList)

        viewModel = MainViewModel(mockRepositorySuccess)

        viewModel.responseStatus.observeForever(responseStatusObserver)
        viewModel.progressBar.observeForever(progressBarObserver)
        viewModel.userList.observeForever(usersListObserver)

        viewModel.getUsers()

        verify(responseStatusObserver).onChanged(mockResponseStatus)
        verify(progressBarObserver).onChanged(mockProgressBar)
        verify(usersListObserver).onChanged(mockUsersList)
    }

    @Test
    fun whenViewModelGetUsers_getFailure_theResponseWillBeFailure() {
        val mockResponseStatus = Constants.failure
        val mockProgressBar = View.GONE
        val mockRecyclerView = View.GONE

        val mockRepositoryFailure = MockRepositoryFailure()

        viewModel = MainViewModel(mockRepositoryFailure)

        viewModel.responseStatus.observeForever(responseStatusObserver)
        viewModel.progressBar.observeForever(progressBarObserver)
        viewModel.recyclerView.observeForever(recyclerViewObserver)

        viewModel.getUsers()

        verify(responseStatusObserver).onChanged(mockResponseStatus)
        verify(progressBarObserver).onChanged(mockProgressBar)
        verify(recyclerViewObserver).onChanged(mockRecyclerView)
    }
}

class MockRepositorySuccess(private val list: List<User>) : DefaultRepository {
    override fun getUsers(listener: ApiListener<List<User>>) {
        listener.onSuccess(list)
    }

}

class MockRepositoryFailure() : DefaultRepository {
    override fun getUsers(listener: ApiListener<List<User>>) {
        listener.onFailure()
    }

}

