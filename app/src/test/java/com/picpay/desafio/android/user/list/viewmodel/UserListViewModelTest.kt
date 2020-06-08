package com.picpay.desafio.android.user.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.repositories.UserRepository
import com.picpay.desafio.domain.result.*
import com.picpay.desafio.domain.usecases.GetUserList
import com.picpay.desafio.android.TestCoroutineRule
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class UserListViewModelTest{

    private val observer: Observer<Result<List<User>>> = mock()
    private val repository: UserRepository = mock()

    private val getUseList = GetUserList(repository)
    private lateinit var viewModel: com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `test getUsers should set livedata as OnLoading`() = testCoroutineRule.runBlockingTest {
        // Given
        getUseList.stub {
            onBlocking { execute() } doReturn flow {
                emit(OnLoading)
            }
        }

        // Then
        viewModel =
            com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel(getUseList)
        val liveData = viewModel.getUsersLiveDate()
        liveData.observeForever(observer)

        // Check
        assert(liveData.value is OnLoading)
    }

    @Test
    fun `test getUsers should set livedata as OnSuccess`() = testCoroutineRule.runBlockingTest {
        // Given
        getUseList.stub {
            onBlocking { execute() } doReturn flow {
                emit(OnSuccess(arrayListOf(User())))
            }
        }

        // Then
        viewModel =
            com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel(getUseList)
        val liveData = viewModel.getUsersLiveDate()
        liveData.observeForever(observer)

        // Check
        assert(liveData.value is OnSuccess)
    }

    @Test
    fun `test getUsers should set livedata as OnComplete`() = testCoroutineRule.runBlockingTest {
        // Given
        getUseList.stub {
            onBlocking { execute() } doReturn flow {
                emit(OnComplete)
            }
        }

        // Then
        viewModel =
            com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel(getUseList)
        val liveData = viewModel.getUsersLiveDate()
        liveData.observeForever(observer)

        // Check
        assert(liveData.value is OnComplete)
    }

    @Test
    fun `test getUsers should set livedata as OnError`() = testCoroutineRule.runBlockingTest {
        // Given
        getUseList.stub {
            onBlocking { execute() } doReturn flow {
                emit(OnError(Exception()))
            }
        }

        // Then
        viewModel =
            com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel(getUseList)
        val liveData = viewModel.getUsersLiveDate()
        liveData.observeForever(observer)

        // Check
        assert(liveData.value is OnError)
    }
}