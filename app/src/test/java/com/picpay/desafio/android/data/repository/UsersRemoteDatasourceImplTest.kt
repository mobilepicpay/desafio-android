package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.resources.UserResources
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersRemoteDatasourceImplTest : BaseTest() {

    private lateinit var datasource: UsersRemoteDatasource
    @RelaxedMockK
    private lateinit var service: PicPayApi

    override fun setUp() {
        super.setUp()
        datasource = UsersRemoteDatasourceImpl(service)
    }


    @Test
    fun`when datasource is called then return a list of users`() = runBlockingTest {
        coEvery {
            service.getUsers()
        } returns UserResources.apiResponseSuccess

        assertThat(datasource.getUsers(), CoreMatchers.equalTo(UserResources.apiResponseSuccess))
    }

    @Test
    fun`when datasource is called then return an empty list`() = runBlockingTest {
        coEvery {
            service.getUsers()
        } returns UserResources.apiResponseEmpty

        assertThat(datasource.getUsers(), CoreMatchers.equalTo(UserResources.apiResponseEmpty))
    }

    @Test
    fun`when datasource is called then return an error`() = runBlockingTest {
        coEvery {
            service.getUsers()
        } returns UserResources.apiResponseError

        assertThat(datasource.getUsers(), CoreMatchers.equalTo(UserResources.apiResponseError))
    }
}