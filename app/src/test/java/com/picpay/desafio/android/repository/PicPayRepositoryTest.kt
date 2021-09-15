package com.picpay.desafio.android.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.mapper.PicPayMapper
import com.picpay.desafio.android.repository.local.PicPayDatabase
import com.picpay.desafio.android.repository.local.UserDAO
import com.picpay.desafio.android.repository.remote.PicPayService
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayRepositoryTest {

    private val dao = mock<UserDAO>()

    private val api = mock<PicPayService>()
    private val database = mock<PicPayDatabase>()
    private val mapper = mock<PicPayMapper>()

    private val repository = PicPayRepositoryImpl(api, database, mapper)

    @Test
    fun insertUsersToLocal_Test() {
        val expectedResult = mock<List<Long>>()

        runBlocking { whenever(mapper.userToUserEntity(any())) doReturn emptyList() }
        whenever(database.userDao()) doReturn dao
        whenever(database.userDao().insert(any())) doReturn expectedResult

        val result = runBlocking { repository.insertUsersToLocal(emptyList()) }
        assert(result == expectedResult)
    }

    @Test
    fun getUsersFromLocal_Test() {
        val expectedResult = mock<List<User>>()

        whenever(database.userDao()) doReturn dao
        whenever(database.userDao().getUsers()) doReturn mock()
        runBlocking { whenever(mapper.userEntityToUser(any())) doReturn expectedResult }

        val result = runBlocking { repository.getUsersFromLocal() }
        assert(result == expectedResult)
    }

    @Test
    fun getUsersFromRemote_Test() {
        val expectedResult = mock<List<User>>()

        runBlocking { whenever(api.getUsers()) doReturn mock() }
        runBlocking { whenever(mapper.userResponseToUser(any())) doReturn expectedResult }

        val result = runBlocking { repository.getUsersFromRemote() }
        assert(result == expectedResult)
    }
}
