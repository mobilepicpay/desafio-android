package com.picpay.desafio.userlist.data.repository

import android.content.Context
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.common.extensions.isError
import com.picpay.desafio.userlist.data.dao.UserListDao
import com.picpay.desafio.userlist.data.respository.UserListRepositoryImpl
import com.picpay.desafio.userlist.data.service.PicPayService
import com.picpay.desafio.userlist.di.UserListModule
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import com.picpay.desafio.userlist.utils.CoroutinesTestRule
import com.picpay.desafio.userlist.utils.DataHelper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.mock.declare
import java.security.InvalidParameterException

@DelicateCoroutinesApi
class UserListRepositoryTest : AutoCloseKoinTest() {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val service = mockk<PicPayService>()
    private val dao = mockk<UserListDao>()
    private val repository: UserListRepository

    init {
        startKoin {
            androidContext(mockk<Context>())
            modules(listOf(UserListModule.modules))
            declare { single { dao } }
        }

        repository = UserListRepositoryImpl(service, get())
    }

    @Test
    fun whenGetListFromDatabase_andListIsEmpty_shouldReturnStatusEmpty() {
        runBlocking {
            coEvery { dao.getList() } returns flow { emit(emptyList<User>()) }
            val data = repository.getListFromDatabase().take(1).toList()

            Assert.assertEquals("Assert response status", Resource.Status.EMPTY, data.last().status)
            Assert.assertEquals("Assert list is empty", true, data.last().value.isNullOrEmpty())
        }
    }

    @Test
    fun whenGetListFromDatabase_andListContainsData_shouldReturnStatusSuccess() {
        runBlocking {
            coEvery { dao.getList() } returns flow { emit(DataHelper.listOfUsers) }
            val data = repository.getListFromDatabase().take(1).toList()

            Assert.assertEquals(
                "Assert response status",
                Resource.Status.SUCCESS,
                data.last().status
            )
            Assert.assertEquals(
                "Assert list is not empty",
                false,
                data.last().value.isNullOrEmpty()
            )

            Assert.assertEquals(
                "Assert list is correctly",
                true,
                DataHelper.listOfUsers == data.last().value
            )
        }
    }

    @Test
    fun whenGetListFromServer_andListContainsData_shouldReturnStatusSuccess() {
        runBlocking {
            coEvery { service.getUsers() } returns DataHelper.responseSuccess
            val data = repository.getListFromServer().take(1).toList()

            Assert.assertEquals(
                "Assert response status",
                Resource.Status.SUCCESS,
                data.last().status
            )
            Assert.assertEquals(
                "Assert list is not empty",
                false,
                data.last().value?.isNullOrEmpty()
            )
            Assert.assertEquals(
                "Assert list is correctly",
                true,
                DataHelper.listOfUsers == data.last().value
            )
        }
    }

    @Test
    fun whenGetListFromServer_andServerHasException_shouldReturnStatusError() {
        runBlocking {
            coEvery { service.getUsers() } returns DataHelper.responseError

            Assert.assertThrows(
                InvalidParameterException::class.java
            ) { runBlocking { repository.getListFromServer().take(1).toList() } }
        }
    }

    @Test
    fun whenGetListFromServer_andServerHasEmptyValue_shouldReturnStatusError() {
        runBlocking {
            coEvery { service.getUsers() } returns DataHelper.responseEmpty

            Assert.assertThrows(
                InvalidParameterException::class.java
            ) { runBlocking { repository.getListFromServer().take(1).toList() } }
        }
    }
}