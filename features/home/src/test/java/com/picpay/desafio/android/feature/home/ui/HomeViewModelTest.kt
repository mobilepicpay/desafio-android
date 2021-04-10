package com.picpay.desafio.android.feature.home.ui

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.feature.home.interactor.user.GetUserListError
import com.picpay.desafio.android.feature.home.interactor.user.GetUserListUseCase
import com.picpay.desafio.android.feature.home.interactor.user.UserEntity
import com.picpay.desafio.android.feature.home.testing.MockKViewModelTest
import com.picpay.desafio.android.feature.home.ui.HomeViewModel.HomeViewEvent
import com.picpay.desafio.android.feature.home.ui.HomeViewModel.HomeViewState
import com.picpay.desafio.android.shared.domain.EntityResult
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Test

internal class HomeViewModelTest : MockKViewModelTest<HomeViewModel, HomeViewState, HomeViewEvent>() {

    @MockK
    private lateinit var getUserListUseCase: GetUserListUseCase

    @Test
    fun `onCreate SHOULD not emit any event WHEN userCase returns Success`() {
        // Given
        coEvery { getUserListUseCase() } returns EntityResult.Success(arrayListOf())
        // When
        viewModel.onCreate()
        // Then
        assertThat(events).hasSize(0)
    }

    @Test
    fun `onCreate SHOULD emit Loading`() {
        // Given
        coEvery { getUserListUseCase() } returns EntityResult.Success(arrayListOf())
        // When
        viewModel.onCreate()
        // Then
        assertThat(states[0]).isInstanceOf(HomeViewState.Loading::class.java)
    }

    @Test
    fun `onCreate SHOULD emit UserList WHEN useCase returns Success`() {
        // Given
        coEvery { getUserListUseCase() } returns EntityResult.Success(arrayListOf())
        // When
        viewModel.onCreate()
        // Then
        assertThat(states).hasSize(2)
        assertThat(states[1]).isInstanceOf(HomeViewState.UserList::class.java)
    }

    @Test
    fun `onCreate SHOULD emit UserList WITH items from useCase`() {
        // Given
        val users = arrayListOf(
            UserEntity(1, "Andre", "Spinel", "imageABC"),
            UserEntity(2, "Wellington", "Wellz", "imageABCE")
        )
        coEvery { getUserListUseCase() } returns EntityResult.Success(users)
        // When
        viewModel.onCreate()
        // Then
        (states[1] as HomeViewState.UserList).run {
            assertThat(list).isEqualTo(users)
        }
    }

    @Test
    fun `onCreate SHOULD emit Error AND SendErrorToast WHEN useCase returns ServerError`() {
        // Given
        coEvery { getUserListUseCase() } returns EntityResult.Error(GetUserListError.ServerError)
        // When
        viewModel.onCreate()
        // Then
        assertThat(states).hasSize(2)
        assertThat(states[1]).isInstanceOf(HomeViewState.Error::class.java)

        assertThat(events).hasSize(1)
        assertThat(events[0]).isInstanceOf(HomeViewEvent.SendErrorToast::class.java)
    }

    @Test
    fun `onCreate SHOULD emit Error AND SendErrorToast WHEN useCase returns NoInternetError`() {
        // Given
        coEvery { getUserListUseCase() } returns EntityResult.Error(GetUserListError.NoInternet)
        // When
        viewModel.onCreate()
        // Then
        assertThat(states).hasSize(2)
        assertThat(states[1]).isInstanceOf(HomeViewState.Error::class.java)

        assertThat(events).hasSize(1)
        assertThat(events[0]).isInstanceOf(HomeViewEvent.SendErrorToast::class.java)
    }
}