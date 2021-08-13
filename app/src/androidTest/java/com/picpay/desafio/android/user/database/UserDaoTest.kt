package com.picpay.desafio.android.user.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.database.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun shouldInsertUsersWhenCallDaoInsertMethod() = runBlocking {
        val users = listOf(
            UserEntity(imageUrl = "img", name = "name", id = 1, userName = "username")
        )

        dao.insertAll(users)
        val daoUsers = dao.getAll()

        assertEquals(users, daoUsers)
    }

    @Test
    fun shouldDeleteUsersWhenCallDaoDeleteAllMethod() = runBlocking {
        val users = listOf(
            UserEntity(imageUrl = "img", name = "name", id = 1, userName = "username")
        )

        dao.insertAll(users)
        dao.deleteAll()
        val daoUsers = dao.getAll()

        assertEquals(daoUsers.size, 0)
    }
}