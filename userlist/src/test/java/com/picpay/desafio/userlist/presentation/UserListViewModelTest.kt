package com.picpay.desafio.userlist.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jraska.livedata.test
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.common.extensions.isError
import com.picpay.desafio.common.extensions.isSuccess
import com.picpay.desafio.userlist.di.UserListModule
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCase
import com.picpay.desafio.userlist.presentation.viewmodel.UserListViewModel
import com.picpay.desafio.userlist.utils.CoroutinesTestRule
import com.picpay.desafio.userlist.utils.DataHelper
import com.picpay.desafio.userlist.utils.DataHelper.flowResponseEmpty
import com.picpay.desafio.userlist.utils.DataHelper.flowResponseError
import com.picpay.desafio.userlist.utils.DataHelper.flowResponseSuccess
import io.mockk.*
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

class UserListViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val useCase = mockk<GetUserListUseCase>()
    private lateinit var viewModel: UserListViewModel

    @Test
    fun whenCreateViewModel_shouldRequestUseList_andServerHasSuccess_shouldGetSuccessStatus() {
        coEvery { useCase.execute(Unit) } returns flowResponseSuccess
        viewModel = UserListViewModel(useCase)

        val result = viewModel.userListResponse.test()

        coVerify(exactly = 1) {
            viewModel.getList()
            useCase.execute(Unit)
        }

        val history = result
            .assertHasValue()
            .assertHistorySize(1)
            .assertNever { it.status == Resource.Status.ERROR }
            .valueHistory()

        assert(history.first().isSuccess())
        assert(history.first().value == DataHelper.listOfUsers)
    }

    @Test
    fun whenCreateViewModel_shouldRequestUseList_andServerHasErrordGetErrorStatus() {
        coEvery { useCase.execute(Unit) } returns flowResponseError
        viewModel = UserListViewModel(useCase)

        val result = viewModel.userListResponse.test()

        coVerify(exactly = 1) {
            viewModel.getList()
            useCase.execute(Unit)
        }

        val history = result
            .assertHasValue()
            .assertHistorySize(1)
            .assertNever { it.status == Resource.Status.SUCCESS }
            .valueHistory()

        assert(history.first().isError())
        assert(history.first().value.isNullOrEmpty())
    }

    @Test
    fun whenCreateViewModel_shouldRequestUseList_andServerIsEmptyGetEmptyStatus() {
        coEvery { useCase.execute(Unit) } returns flowResponseEmpty
        viewModel = UserListViewModel(useCase)

        val result = viewModel.userListResponse.test()

        coVerify(exactly = 1) {
            viewModel.getList()
            useCase.execute(Unit)
        }

        val history = result
            .assertHasValue()
            .assertHistorySize(1)
            .assertNever { it.status == Resource.Status.ERROR }
            .assertNever { it.status == Resource.Status.SUCCESS }
            .valueHistory()

        assert(history.first().status == Resource.Status.EMPTY)
        assert(history.first().value.isNullOrEmpty())
    }
}