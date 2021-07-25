package com.picpay.desafio.android.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
class UserDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun insertUsers() = runBlockingTest {
        val listUsers = listOf(UserDb(1, "url", "alessandro", "ale"))
        userDao.insertUsers(listUsers)
        val toList = userDao.getAllUsers().first()
        Truth.assertThat(listUsers).isEqualTo(toList)
    }

    @Test
    fun deleteAllUsers() = runBlockingTest {
        val listUsers = listOf(UserDb(1, "url", "alessandro", "ale"))
        userDao.insertUsers(listUsers)

        val toList = userDao.getAllUsers().first()
        Truth.assertThat(listUsers).isEqualTo(toList)

        userDao.deleteAllUsers()

        val toList2 = userDao.getAllUsers().first()
        Truth.assertThat(toList2).isEmpty()

    }

}