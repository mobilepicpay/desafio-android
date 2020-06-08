package com.picpay.desafio.android.user.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.repositories.UserRepository
import com.picpay.desafio.domain.result.OnError
import com.picpay.desafio.domain.result.OnSuccess
import com.picpay.desafio.domain.result.Result
import com.picpay.desafio.domain.usecases.GetUserDetail
import com.picpay.desafio.android.TestCoroutineRule
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class UserDetailViewModelTest {

    private val userId = 1

    private val observer: Observer<Result<User>> = mock()
    private val repository: UserRepository = mock()

    private val getUserDetail = GetUserDetail(repository)
    private val viewModel =
        com.picpay.desafio.android.features.user.detail.viewmodel.UserDetailViewModel(getUserDetail)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `test getUser should set livedata as OnSuccess `() = testCoroutineRule.runBlockingTest {
        // Given
        getUserDetail.stub {
            onBlocking { execute(userId) } doReturn flow {
                emit(OnSuccess(User()))
            }
        }

        // Then
        val liveData = viewModel.getUserLiveDate()
        liveData.observeForever(observer)
        viewModel.getUser(userId)

        // Check
        assertTrue(liveData.value is OnSuccess)
    }

    @Test
    fun `test getUser should set livedata as OnError`() = testCoroutineRule.runBlockingTest {
        // Given
        getUserDetail.stub {
            onBlocking { execute(userId) } doReturn flow {
                emit(OnError(Exception()))
            }
        }

        // Then
        val liveData = viewModel.getUserLiveDate()
        liveData.observeForever(observer)
        viewModel.getUser(userId)

        // Check
        assertTrue(liveData.value is OnError)
    }
}