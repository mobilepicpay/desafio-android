package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.repositories.UsersRepository
import com.picpay.desafio.android.resources.UserResources
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetRemoteUsersUseCaseTest : BaseTest() {

    private lateinit var useCase: GetRemoteUsersUseCase
    @RelaxedMockK
    private lateinit var repository: UsersRepository

    @Before
    override fun setUp() {
        super.setUp()
        useCase = GetRemoteUsersUseCase(repository)
    }


    @Test
    fun`when usecase has called then return a list of users`() = runBlockingTest {
        every {
            runBlocking { repository.getRemoteUsers() }
        } returns Result.Success(UserResources.dummyListUserEntity)

        val expected = Result.Success(UserResources.dummyListUserEntity)

        MatcherAssert.assertThat(useCase(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when usecase has called then return a empty list of users`() = runBlockingTest {
        every {
            runBlocking { repository.getRemoteUsers() }
        } returns Result.Success(UserResources.emptyListUserEntity)

        val expected = Result.Success(UserResources.emptyListUserEntity)

        MatcherAssert.assertThat(useCase(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when usecase has called then return an error`() = runBlockingTest {
        every {
            runBlocking { repository.getRemoteUsers() }
        } returns Result.Error(UserResources.dummyException)

        MatcherAssert.assertThat(useCase(), CoreMatchers.instanceOf(Result.Error::class.java))
    }
}