package com.picpay.desafio.android.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.repository.PicPayRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayInteractorTest {

    private val repository = mock<PicPayRepository>()

    private val interactor = PicPayInteractorImpl(repository)

    @Test
    fun getUsers_WithLocal_Test() {
        val local = mock<List<UserEntity>>()
        val users = mock<List<User>>()

        runBlocking { whenever(repository.getUsersFromLocal()) doReturn local }
        whenever(local.isEmpty()) doReturn false

        whenever(repository.mapperUserEntityToUser(any())) doReturn users
        whenever(users.isEmpty()) doReturn false

        val result = runBlocking { interactor.getUsers() }
        assert(result is PicPayState.GetUsers.Data)
    }

    @Test
    fun getUsers_WithoutLocal_WithRemote_Test() {
        val local = mock<List<UserEntity>>()
        val remote = mock<List<UserResponse>>()
        val users = mock<List<User>>()
        val usersIds = mock<List<Long>>()

        runBlocking { whenever(repository.getUsersFromLocal()) doReturn local }
        whenever(local.isEmpty()) doReturn true

        runBlocking { whenever(repository.getUsersFromRemote()) doReturn remote }
        whenever(remote.isNotEmpty()) doReturn true

        whenever(repository.mapperUserResponseToUserEntity(any())) doReturn local
        runBlocking { whenever(repository.insertUsersToLocal(any())) doReturn usersIds }

        whenever(repository.mapperUserResponseToUser(any())) doReturn users
        whenever(users.isEmpty()) doReturn false

        val result = runBlocking { interactor.getUsers() }
        assert(result is PicPayState.GetUsers.Data)
    }

    @Test
    fun getUsers_WithoutLocal_WithoutRemote_Test() {
        val local = mock<List<UserEntity>>()
        val remote = mock<List<UserResponse>>()
        val users = mock<List<User>>()

        runBlocking { whenever(repository.getUsersFromLocal()) doReturn local }
        whenever(local.isEmpty()) doReturn true

        runBlocking { whenever(repository.getUsersFromRemote()) doReturn remote }
        whenever(remote.isNotEmpty()) doReturn false

        whenever(repository.mapperUserResponseToUser(any())) doReturn users
        whenever(users.isEmpty()) doReturn true

        val result = runBlocking { interactor.getUsers() }
        assert(result is PicPayState.GetUsers.Empty)
    }
}