package com.picpay.desafio.feature.contactlist.repository.impl

import com.google.common.truth.Truth
import com.picpay.desafio.feature.contactlist.repository.UserInternalDataSource
import com.picpay.desafio.feature.contactlist.repository.UserRemoteDataSource
import com.picpay.desafio.feature.contactlist.usecase.UserDomain
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserRepositoryTest {
    private val userRemoteDataSource: UserRemoteDataSource = mockk()
    private val userInternalDataSource: UserInternalDataSource = mockk()

    private val repository = UserRepositoryImpl(userRemoteDataSource, userInternalDataSource)

    @Test
    fun getUsers_getUserRemoteDataSource_ExpectedListOfUser() = runTest {
        val expected = listOf(
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

        prepareScenario(
            userDataRemoteSourceResult = listUserResponse(),
        )

        val actual = repository.getUsers()

        Truth.assertThat(actual).isEqualTo(Result.success(expected))
        coVerify(exactly = 1) { userInternalDataSource.insertUsers(expected) }
        coVerify(exactly = 1) { userInternalDataSource.getUsers() }
    }

    @Test
    fun getUsers_getUserInternalDataSource_ExpectedListOfUser() = runTest {
        val expected = listOf(
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

        prepareScenario(
            userDataInternalSourceResult = listUserResponse(),
        )

        val actual = repository.getUsers()

        Truth.assertThat(actual).isEqualTo(Result.success(expected))
        coVerify(exactly = 0) { userRemoteDataSource.getUsers() }
        coVerify(exactly = 0) { userInternalDataSource.insertUsers(any()) }
    }

    @Test
    fun getUsers_ExternalDataSourceError_returnResultFailure() = runTest {
        val exception = Exception("")
        prepareScenario(userDataSourceError = exception)

        val expected = Result.failure<Exception>(exception)
        val actual = repository.getUsers()

        Truth.assertThat(actual).isEqualTo(expected)

        coVerify(exactly = 1) { userRemoteDataSource.getUsers() }
        coVerify(exactly = 1) { userInternalDataSource.getUsers() }
        coVerify(exactly = 0) { userInternalDataSource.insertUsers(any()) }
    }

    @Test
    fun getUsers_InternalDataSourceInsertUserError_returnResultFailure() = runTest {
        val exception = Exception("")
        prepareScenario(insertUserInternalDataSourceError = exception)

        val expected = Result.failure<Exception>(exception)
        val actual = repository.getUsers()

        Truth.assertThat(actual).isEqualTo(expected)
        coVerify(exactly = 1) { userRemoteDataSource.getUsers() }
        coVerify(exactly = 1) { userInternalDataSource.getUsers() }
    }

    @Test
    fun getUsers_InternalDataSourceGetUserError_returnResultFailure() = runTest {
        val exception = Exception("")
        prepareScenario(getUserInternalDataSourceError = exception)

        val expected = Result.failure<Exception>(exception)
        val actual = repository.getUsers()

        Truth.assertThat(actual).isEqualTo(expected)
        coVerify(exactly = 0) { userRemoteDataSource.getUsers() }
        coVerify(exactly = 0) { userInternalDataSource.insertUsers(any()) }
        coVerify(exactly = 1) { userInternalDataSource.getUsers() }
    }

    private fun prepareScenario(
        userDataSourceError: Throwable? = null,
        getUserInternalDataSourceError: Throwable? = null,
        userDataRemoteSourceResult: List<UserDomain> = listOf(),
        userDataInternalSourceResult: List<UserDomain> = listOf(),
        insertUserInternalDataSourceError: Throwable? = null,
    ) {
        userDataSourceError?.let {
            coEvery { userRemoteDataSource.getUsers() } throws userDataSourceError
        } ?: run {
            coEvery { userRemoteDataSource.getUsers() } returns userDataRemoteSourceResult
        }

        getUserInternalDataSourceError?.let {
            coEvery { userInternalDataSource.getUsers() } throws getUserInternalDataSourceError
        } ?: run {
            coEvery { userInternalDataSource.getUsers() } returns userDataInternalSourceResult
        }

        insertUserInternalDataSourceError?.let {
            coEvery {
                userInternalDataSource.insertUsers(any())
            } throws insertUserInternalDataSourceError
        } ?: run {
            coEvery {
                userInternalDataSource.insertUsers(any())
            } just runs
        }
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