package com.picpay.desafio.contactlist.usecase.impl

import com.google.common.truth.Truth
import com.picpay.desafio.feature.contactlist.repository.UserRepository
import com.picpay.desafio.feature.contactlist.usecase.UserDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetUsersUseCaseTest {
    private val userRepository: UserRepository = mockk()

    private val usecase = GetUsersUseCaseImpl(userRepository)

    @Test
    fun invoke_receiveEmptyList_expectResultSuccessWithEmptyList() = runTest {
        prepareScenario()
        val expected = Result.success(listOf<UserDomain>())

        val actual = usecase()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun invoke_receiveUsersList_expectedResultSuccessUserList() = runTest {
        val expected = Result.success(listUserResponse())
        prepareScenario(expected)

        val actual = usecase()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun invoke_receiveError_expectedResultFailure() = runTest {
        val expected = Result.failure<List<UserDomain>>(ArrayIndexOutOfBoundsException())
        prepareScenario(expected)

        val actual = usecase()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    private fun prepareScenario(
        userRepositoryResult: Result<List<UserDomain>> = Result.success(listOf())
    ) {
        coEvery { userRepository.getUsers() } returns userRepositoryResult
    }

    private fun listUserResponse() = listOf(
        UserDomain(
            img = "img",
            name = "name",
            id = 1,
            username = "username"
        ),
        UserDomain(
            img = "img",
            name = "name",
            id = 2,
            username = "username"
        ),
        UserDomain(
            img = "img",
            name = "name",
            id = 3,
            username = "username"
        )
    )
}