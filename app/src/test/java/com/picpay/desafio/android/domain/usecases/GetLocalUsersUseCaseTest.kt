package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.resources.UserResources
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.repositories.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import org.hamcrest.MatcherAssert.assertThat


import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class GetLocalUsersUseCaseTest : BaseTest() {

    private lateinit var useCase: GetLocalUsersUseCase
    @RelaxedMockK
    private lateinit var repository: UsersRepository

    @Before
    override fun setUp() {
        super.setUp()
        useCase = GetLocalUsersUseCase(repository)
    }


    @Test
    fun`when usecase has called then return a list of users`() = runBlockingTest {
        every {
           runBlocking { repository.getCachedUsers() }
        } returns Result.Success(UserResources.dummyListUserEntity)

        val expected = Result.Success(UserResources.dummyListUserEntity)

        assertThat(useCase(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when usecase has called then return a empty list of users`() = runBlockingTest {
        every {
            runBlocking { repository.getCachedUsers() }
        } returns Result.Success(UserResources.emptyListUserEntity)

        val expected = Result.Success(UserResources.emptyListUserEntity)

        assertThat(useCase(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when usecase has called then return an error`() = runBlockingTest {
        every {
            runBlocking { repository.getCachedUsers() }
        } returns Result.Error(UserResources.getDummyException())

        assertThat(useCase(), CoreMatchers.instanceOf(Result.Error::class.java))
    }
}