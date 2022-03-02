package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.repositories.UsersRepository
import com.picpay.desafio.android.resources.UserResources
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersRepositoryImplTest : BaseTest() {

    private lateinit var repository: UsersRepository
    @RelaxedMockK
    private lateinit var remoteDataSource: UsersRemoteDatasource
    @RelaxedMockK
    private lateinit var localDataSource: UsersLocalDatasource

    override fun setUp() {
        super.setUp()
        repository = UsersRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun`when repository is called then return a list of users remotely`() = runBlockingTest {
        every {
           runBlocking { remoteDataSource.getUsers() }
        } returns UserResources.apiResponseSuccess

        val expected = Result.Success(UserResources.dummyListUserEntity)

        MatcherAssert.assertThat(repository.getRemoteUsers(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when repository is called then return an empty list of users remotely`() = runBlockingTest {
        every {
            runBlocking { remoteDataSource.getUsers() }
        } returns UserResources.apiResponseEmpty

        val expected = Result.Success(UserResources.emptyListUserEntity)

        MatcherAssert.assertThat(repository.getRemoteUsers(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when repository is called then return a list of users locally`() = runBlockingTest {
        every {
            runBlocking { localDataSource.getUsers() }
        } returns UserResources.dummyListUserResponse

        val expected = Result.Success(UserResources.dummyListUserEntity)

        MatcherAssert.assertThat(repository.getCachedUsers(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when repository is called then return an empty list of users locally`() = runBlockingTest {
        every {
            runBlocking { localDataSource.getUsers() }
        } returns UserResources.emptyListUserResponse

        val expected = Result.Success(UserResources.emptyListUserEntity)

        MatcherAssert.assertThat(repository.getCachedUsers(), CoreMatchers.equalTo(expected))
    }

    @Test
    fun`when repository is called then return an error`() = runBlockingTest {
        every {
            runBlocking { remoteDataSource.getUsers() }
        } throws UserResources.dummyException
        every {
            runBlocking { localDataSource.getUsers() }
        } throws UserResources.dummyException

        val expected = Result.Error::class.java

        MatcherAssert.assertThat(repository.getRemoteUsers(), CoreMatchers.instanceOf(expected))
        MatcherAssert.assertThat(repository.getCachedUsers(), CoreMatchers.instanceOf(expected))
    }
}