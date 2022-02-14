package com.picpay.desafio.userlist.domain

import android.content.Context
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.di.UserListModule
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCase
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCaseImpl
import com.picpay.desafio.userlist.utils.DataHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import java.security.InvalidParameterException


class GetUserListUseCaseTest : AutoCloseKoinTest() {

    private val repository = mockk<UserListRepository>()
    private val useCase: GetUserListUseCase

    init {
        startKoin {
            androidContext(mockk<Context>())
            modules(listOf(UserListModule.modules))
        }

        useCase = GetUserListUseCaseImpl(repository)
    }

    @Test
    fun whenGetResponseFromServer_andItHasData_shouldSaveLocally_withoutAnyCallToListFromDatabase() {
        runBlocking {
            coEvery { repository.getListFromServer() } returns DataHelper.flowResponseSuccess
            coEvery { repository.saveListFromServer(any()) } returns Unit

            val response = useCase.execute(Unit).take(2).toList()

            coVerify(exactly=1) {
                repository.saveListFromServer(any())
                repository.getListFromServer()
            }

            coVerify(exactly = 0) { repository.getListFromDatabase() }

            Assert.assertEquals("Assert has Loading status", Resource.Status.LOADING, response.first().status)
            Assert.assertEquals("Assert response status", Resource.Status.SUCCESS, response.last().status)
            Assert.assertEquals("Assert list is not empty", false, response.last().value.isNullOrEmpty())
        }
    }

    @Test
    fun whenGetResponseFromServer_andItDataIsEmpty_shouldNotSaveLocally_withoutAnyCallToListFromDatabase() {
        runBlocking {
            coEvery { repository.getListFromServer() } returns DataHelper.flowResponseEmpty
            coEvery { repository.saveListFromServer(any()) } returns Unit

            val response = useCase.execute(Unit).take(2).toList()

            coVerify(exactly=1) {
                repository.getListFromServer()
            }

            coVerify(exactly = 0) {
                repository.getListFromDatabase()
                repository.saveListFromServer(any())
            }

            Assert.assertEquals("Assert has Loading status", Resource.Status.LOADING, response.first().status)
            Assert.assertEquals("Assert response status", Resource.Status.EMPTY, response.last().status)
            Assert.assertEquals("Assert list is empty", true, response.last().value.isNullOrEmpty())
        }
    }

    @Test
    fun whenGetResponseFromServer_andServerThrowsAnException_shouldGetFromLocal() {
        runBlocking {
            coEvery { repository.getListFromServer() } returns flow { throw Exception() }
            coEvery { repository.getListFromDatabase() } returns DataHelper.flowResponseSuccess

            val response = useCase.execute(Unit).take(2).toList()

            coVerify(exactly=1) {
                repository.getListFromServer()
                repository.getListFromDatabase()
            }

            coVerify(exactly = 0) { repository.saveListFromServer(any()) }

            Assert.assertEquals("Assert has Loading status", Resource.Status.LOADING, response.first().status)
            Assert.assertEquals("Assert response status", Resource.Status.SUCCESS, response.last().status)
            Assert.assertEquals("Assert list is not empty", false, response.last().value.isNullOrEmpty())
        }
    }

    @Test
    fun whenGetResponseFromServer_andServerThrowsAnException_andDatabaseIsEmpty_shouldGetEmptyResult() {
        runBlocking {
            coEvery { repository.getListFromServer() } returns flow { throw Exception() }
            coEvery { repository.getListFromDatabase() } returns DataHelper.flowResponseEmpty

            val response = useCase.execute(Unit).take(2).toList()

            coVerify(exactly=1) {
                repository.getListFromServer()
                repository.getListFromDatabase()
            }

            coVerify(exactly = 0) { repository.saveListFromServer(any()) }

            Assert.assertEquals("Assert has Loading status", Resource.Status.LOADING, response.first().status)
            Assert.assertEquals("Assert response status", Resource.Status.EMPTY, response.last().status)
            Assert.assertEquals("Assert list is empty", true, response.last().value.isNullOrEmpty())
        }
    }

    @Test
    fun whenGetResponseFromServer_andServerThrowsAnException_andDatabaseThrowsException_shouldGetErrorResult() {
        runBlocking {
            coEvery { repository.getListFromServer() } returns flow { throw Exception() }
            coEvery { repository.getListFromDatabase() } returns flow { throw InvalidParameterException() }

            val response = useCase.execute(Unit).take(2).toList()

            coVerify(exactly=1) {
                repository.getListFromServer()
                repository.getListFromDatabase()
            }

            coVerify(exactly = 0) { repository.saveListFromServer(any()) }

            Assert.assertEquals("Assert has Loading status", Resource.Status.LOADING, response.first().status)
            Assert.assertEquals("Assert response status", Resource.Status.ERROR, response.last().status)
            Assert.assertEquals("Assert list is empty", true, response.last().error is InvalidParameterException)
        }
    }
}