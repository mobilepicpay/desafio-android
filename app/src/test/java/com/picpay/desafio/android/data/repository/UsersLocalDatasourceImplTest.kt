package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.resources.UserResources
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersLocalDatasourceImplTest : BaseTest() {

    private lateinit var datasource: UsersLocalDatasource
    @RelaxedMockK
    private lateinit var userDao: UserDao

    override fun setUp() {
        super.setUp()
        datasource = UsersLocalDatasourceImpl(userDao)
    }

    @Test
    fun`when datasource is called then return a list of users`() = runBlockingTest {
        every {
            userDao.getUsers()
        } returns UserResources.dummyListUserResponse

        MatcherAssert.assertThat(datasource.getUsers(), CoreMatchers.equalTo(UserResources.dummyListUserResponse))
    }

    @Test
    fun`when datasource is called then return an empty list`() = runBlockingTest {
        every {
            userDao.getUsers()
        } returns UserResources.emptyListUserResponse

        MatcherAssert.assertThat(datasource.getUsers(), CoreMatchers.equalTo(UserResources.emptyListUserResponse))
    }

}