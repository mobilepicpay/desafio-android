package com.picpay.desafio.android.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.repository.local.PicPayDatabase
import com.picpay.desafio.android.repository.local.UserDAO
import com.picpay.desafio.android.repository.remote.PicPayService
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayRepositoryTest {

    private val dao = mock<UserDAO>()

    private val api = mock<PicPayService>()
    private val database = mock<PicPayDatabase>()

    private val repository = PicPayRepositoryImpl(api, database)

    @Test
    fun insertUsersToLocal_Test() {
        val expectedResult = mock<List<Long>>()

        whenever(database.userDao()) doReturn dao
        whenever(database.userDao().insert(any())) doReturn expectedResult

        val result = runBlocking { repository.insertUsersToLocal(emptyList()) }
        assert(result == expectedResult)
    }

    @Test
    fun getUsersFromLocal_Test() {
        val expectedResult = mock<List<UserEntity>>()

        whenever(database.userDao()) doReturn dao
        whenever(database.userDao().getUsers()) doReturn expectedResult

        val result = runBlocking { repository.getUsersFromLocal() }
        assert(result == expectedResult)
    }

    @Test
    fun getUsersFromRemote_Test() {
        val expectedResult = mock<List<UserResponse>>()

        runBlocking { whenever(api.getUsers()) doReturn expectedResult }

        val result = runBlocking { repository.getUsersFromRemote() }
        assert(result == expectedResult)
    }
}