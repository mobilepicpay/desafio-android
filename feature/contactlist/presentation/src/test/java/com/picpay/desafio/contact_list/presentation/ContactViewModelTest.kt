package com.picpay.desafio.contact_list.presentation

import com.google.common.truth.Truth
import com.picpay.desafio.contact_list.presentation.model.UserUi
import com.picpay.desafio.contact_list.presentation.model.mapper.UserDomainToUserUiMapper
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUiError
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUiState
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUserEvent
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactViewModel
import com.picpay.desafio.feature.contactlist.usecase.GetUsersUseCase
import com.picpay.desafio.feature.contactlist.usecase.UserDomain
import com.picpay.desafio.testlib.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class ContactViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUsersUseCase: GetUsersUseCase = mockk()
    private val mapper: UserDomainToUserUiMapper = mockk()

    private val viewModel = ContactViewModel(getUsersUseCase, ContactUiState(), mapper)

    @Test
    fun onCreateViewModel_withoutReceiveEvent_UiStateIsEmpty() {

        val expected = ContactUiState(
            userList = null,
            loading = false,
            error = null
        )
        val actual = viewModel.uiState.value

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun setUserState_receiveOnInit_sendUserListToUi() = runTest {
        val mapperResult = listOf(
            UserUi(img = "img-url", name = "name", id = 1, username = "username"),
            UserUi(img = "img-url", name = "name", id = 2, username = "username"),
            UserUi(img = "img-url", name = "name", id = 3, username = "username"),
        )

        val userCaseResult = Result.success(
            listOf(
                UserDomain(img = "img-url", name = "name", id = 1, username = "username"),
                UserDomain(img = "img-url", name = "name", id = 2, username = "username"),
                UserDomain(img = "img-url", name = "name", id = 3, username = "username"),
            )
        )

        prepareScenario(
            mapperResult = mapperResult,
            useCaseResult = userCaseResult
        )

        val expected = ContactUiState(
            loading = false,
            userList = mapperResult,
            error = null
        )

        viewModel.setUserEvent(ContactUserEvent.OnInitScreen)
        runCurrent()

        val actual = viewModel.uiState.first()

        Truth.assertThat(actual).isEqualTo(expected)
        verify(exactly = 3) { mapper.mapToUi(any()) }
    }

    @Test
    fun setUserState_receiveOnInit_sendUserEmptyListToUi() = runTest {
        prepareScenario()

        val expected = ContactUiState(
            loading = false,
            userList = listOf(),
            error = null
        )

        viewModel.setUserEvent(ContactUserEvent.OnInitScreen)
        runCurrent()

        val actual = viewModel.uiState.first()

        Truth.assertThat(actual).isEqualTo(expected)
        verify(exactly = 0) { mapper.mapToUi(any()) }
    }

    @Test
    fun setUserState_receiveOnInit_sendUserUnknownUiErrorToUi() = runTest {
        prepareScenario(useCaseResult = Result.failure(Exception()))

        val expected = ContactUiState(
            loading = false,
            userList = null,
            error = ContactUiError.UnknownError
        )

        viewModel.setUserEvent(ContactUserEvent.OnInitScreen)
        runCurrent()

        val actual = viewModel.uiState.first()

        Truth.assertThat(actual).isEqualTo(expected)
        verify(exactly = 0) { mapper.mapToUi(any()) }
    }

    @Test
    fun setUserState_receiveOnInit_sendUserInternetUiErrorToUi() = runTest {
        prepareScenario(useCaseResult = Result.failure(UnknownHostException()))

        val expected = ContactUiState(
            loading = false,
            userList = null,
            error = ContactUiError.InternetConnection
        )

        viewModel.setUserEvent(ContactUserEvent.OnInitScreen)
        runCurrent()

        val actual = viewModel.uiState.first()

        Truth.assertThat(actual).isEqualTo(expected)
        verify(exactly = 0) { mapper.mapToUi(any()) }
    }

    private fun prepareScenario(
        useCaseResult: Result<List<UserDomain>> = Result.success(listOf()),
        mapperResult: List<UserUi> = listOf(UserUi("", "", 1, ""))
    ) {
        every { mapper.mapToUi(any()) } returnsMany mapperResult
        coEvery { getUsersUseCase() } returns useCaseResult
    }
}