package com.picpay.desafio.android.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse
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

    @Test
    fun mapperUserEntityToUser_Test() {
        val expectedResult = mock<List<User>>()

        runBlocking { whenever(mapper.userEntityToUser(any())) doReturn expectedResult }

        val result = runBlocking { repository.mapperUserEntityToUser(mock()) }
        assert(result == expectedResult)
    }

    @Test
    fun mapperUserResponseToUser_Test() {
        val expectedResult = mock<List<User>>()

        runBlocking { whenever(mapper.userResponseToUser(any())) doReturn expectedResult }

        val result = runBlocking { repository.mapperUserResponseToUser(mock()) }
        assert(result == expectedResult)
    }

    @Test
    fun mapperUserResponseToUserEntity_Test() {
        val expectedResult = mock<List<UserEntity>>()

        runBlocking { whenever(mapper.userResponseToUserEntity(any())) doReturn expectedResult }

        val result = runBlocking { repository.mapperUserResponseToUserEntity(mock()) }
        assert(result == expectedResult)
    }
}
