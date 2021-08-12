package com.picpay.desafio.android.modules.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.repositories.UserRepository
import com.picpay.desafio.android.utils.pokos.ErrorMessageViewObject
import com.picpay.desafio.android.utils.pokos.UserViewItem
import com.picpay.desafio.android.utils.sealeds.DataConsumptionStatus
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var userViewItemsObserver: Observer<List<UserViewItem>>

    @MockK
    private lateinit var requestErrorObserver: Observer<ErrorMessageViewObject>

    @MockK
    private lateinit var showSwipeToRefreshLoadingObserver: Observer<Boolean>

    @MockK
    private lateinit var showLoadingObserver: Observer<Boolean>

    @MockK
    private lateinit var userRepository: UserRepository

    private lateinit var mainViewModel: MainViewModel

    private val users = listOf(
        User(1, "John", "image url", "John10"),
        User(2, "Ana", "image url", "Ana07")
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `getUsers, when loading status true is emitted and there aren't users loaded yet, it notifies to show loading`() {
        coEvery { userRepository.getUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Success(emptyList<User>()))
                emit(DataConsumptionStatus.Loading(true))
            }
        )

        initViewModel()
        verify { showLoadingObserver.onChanged(true) }
    }

    @Test
    fun `getUsers, when loading status true is emitted and there are users loaded, it doesn't notify to show loading`() {
        coEvery { userRepository.getUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Success(getMockedUsers()))
                emit(DataConsumptionStatus.Loading(true))
            }
        )

        initViewModel()
        verify(exactly = 0) { showLoadingObserver.onChanged(true) }
    }

    @Test
    fun `getUsers, when loading status false is emitted and the loading isn't being shown, it doesn't notify to hide loading`() {
        coEvery { userRepository.getUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Success(getMockedUsers()))
                emit(DataConsumptionStatus.Loading(true))
                emit(DataConsumptionStatus.Loading(false))
            }
        )

        initViewModel()
        verify(exactly = 0) { showLoadingObserver.onChanged(false) }
    }

    @Test
    fun `getUsers, when success status is emitted, it updates the user view items`() {
        coEvery { userRepository.getUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Success(users))
            }
        )

        val expectedUserViewItems = listOf(
            users.first().run { buildUserViewItem(this) },
            users.last().run { buildUserViewItem(this) }
        )

        initViewModel()
        verify { userViewItemsObserver.onChanged(expectedUserViewItems) }
    }

    @Test
    fun `getUsers, when error status is emitted, it notifies to show an error`() {
        coEvery { userRepository.getUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Error(DataErrorException.ApiErrorException.NotFoundException))
            }
        )

        initViewModel()
        verify {
            requestErrorObserver.onChanged(
                ErrorMessageViewObject(
                    DataErrorException.ApiErrorException.NotFoundException.messageResId,
                    DataErrorException.ApiErrorException.NotFoundException.actionResId,
                )
            )
        }
    }

    @Test
    fun `refreshUsers, when loading status true is emitted and there aren't users loaded yet, it notifies to show loading`() {
        coEvery { userRepository.getUsers() }.returns(flow {})
        coEvery { userRepository.refreshUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Loading(true))
            }
        )

        initViewModel()
        mainViewModel.onRefreshUsers()
        verify { showSwipeToRefreshLoadingObserver.onChanged(true) }
    }

    @Test
    fun `refreshUsers, when success status is emitted, it updates the user view items`() {
        coEvery { userRepository.getUsers() }.returns(flow {})
        coEvery { userRepository.refreshUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Success(users))
            }
        )

        val expectedUserViewItems = listOf(
            users.first().run { buildUserViewItem(this) },
            users.last().run { buildUserViewItem(this) }
        )

        initViewModel()
        mainViewModel.onRefreshUsers()
        verify { userViewItemsObserver.onChanged(expectedUserViewItems) }
    }

    @Test
    fun `refreshUsers, when error status is emitted, it notifies to show an error`() {
        coEvery { userRepository.getUsers() }.returns(flow {})
        coEvery { userRepository.refreshUsers() }.returns(
            flow {
                emit(DataConsumptionStatus.Error(DataErrorException.ApiErrorException.NetworkConnectionException))
            }
        )

        initViewModel()
        mainViewModel.onRefreshUsers()
        verify {
            requestErrorObserver.onChanged(
                ErrorMessageViewObject(
                    DataErrorException.ApiErrorException.NetworkConnectionException.messageResId,
                    DataErrorException.ApiErrorException.NetworkConnectionException.actionResId,
                )
            )
        }
    }

    private fun initViewModel() {
        mainViewModel = MainViewModel(userRepository)
        setupObservers()
    }

    private fun getMockedUsers(): List<User> {
        return listOf(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
    }

    private fun buildUserViewItem(user: User): UserViewItem {
        return user.run {
            UserViewItem(name, image, username)
        }
    }

    private fun setupObservers() {
        mainViewModel.userViewItemsLiveData.observeForever(userViewItemsObserver)
        mainViewModel.requestErrorLiveData.observeForever(requestErrorObserver)
        mainViewModel.showSwipeToRefreshLoadingLiveData.observeForever(showSwipeToRefreshLoadingObserver)
        mainViewModel.showLoadingLiveData.observeForever(showLoadingObserver)
    }
}
