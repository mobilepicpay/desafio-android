package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.entities.UserEntity
import com.picpay.desafio.android.domain.usecases.GetLocalUsersUseCase
import com.picpay.desafio.android.domain.usecases.GetRemoteUsersUseCase
import com.picpay.desafio.android.presentation.common.Event
import com.picpay.desafio.android.presentation.viewmodels.UserListViewModel
import com.picpay.desafio.android.resources.UserResources
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class UserListViewModelTest : BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewmodel: UserListViewModel
    @RelaxedMockK
    private lateinit var remoteUseCase: GetRemoteUsersUseCase
    @RelaxedMockK
    private lateinit var localUseCase: GetLocalUsersUseCase

    @RelaxedMockK
    lateinit var loadingObs: Observer<Boolean>
    @RelaxedMockK
    lateinit var usersObs: Observer<List<UserEntity>>
    @RelaxedMockK
    lateinit var errorObs: Observer<Event<String>>


    override fun setUp() {
        super.setUp()
        Dispatchers.setMain(testDispatcher)
        initViewModel()
    }

    private fun initViewModel() {
        viewmodel = UserListViewModel(localUseCase, remoteUseCase).apply {
            dataLoading.observeForever(loadingObs)
            error.observeForever(errorObs)
            users.observeForever(usersObs)
        }
    }

    override fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        super.tearDown()
    }

    @Test
    fun `should initialize correct calling getUsers remotely`() = testDispatcher.runBlockingTest {
        coEvery { remoteUseCase() } returns Result.Success(UserResources.dummyListUserEntity)
        coEvery { localUseCase() } returns Result.Success(UserResources.dummyListUserEntity)

       viewmodel.getUsers()

        coVerifyAll {
            loadingObs.onChanged(true)
            loadingObs.onChanged(false)
            usersObs.onChanged(UserResources.dummyListUserEntity)
            errorObs wasNot Called
        }
        coVerify(exactly = 1) { remoteUseCase.invoke() }
        coVerify(exactly = 0) { localUseCase.invoke() }
    }

    @Test
    fun `should call local cache when remote call fails`() = testDispatcher.runBlockingTest {

        coEvery { remoteUseCase() } returns Result.Error(UserResources.dummyException)
        coEvery { localUseCase() } returns Result.Success(UserResources.dummyListUserEntity)

        viewmodel.getUsers()

        coVerifyAll {
            loadingObs.onChanged(true)
            loadingObs.onChanged(false)
            usersObs.onChanged(UserResources.dummyListUserEntity)
            errorObs.onChanged(Event("Loaded local users"))
        }
        coVerify(exactly = 1) { remoteUseCase.invoke() }
        coVerify(exactly = 1) { localUseCase.invoke() }
    }

    @Test
    fun `should emit error when call remote and local fails`() = testDispatcher.runBlockingTest {

        coEvery { remoteUseCase() } returns Result.Error(UserResources.dummyException)
        coEvery { localUseCase() } returns Result.Error(UserResources.dummyException)

        viewmodel.getUsers()

        coVerifyAll {
            loadingObs.onChanged(true)
            loadingObs.onChanged(false)
            usersObs wasNot Called
            errorObs.onChanged(Event(UserResources.dummyException.message!!))
        }
        coVerify(exactly = 1) { remoteUseCase.invoke() }
        coVerify(exactly = 1) { localUseCase.invoke() }
    }

}