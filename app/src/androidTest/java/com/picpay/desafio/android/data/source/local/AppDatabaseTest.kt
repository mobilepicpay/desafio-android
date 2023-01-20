package com.picpay.desafio.android.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.data.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeUserAndReadInList() = runTest {
        val userList = listOf(
            UserEntity("img", "name", 1L, "user"),
            UserEntity("img2", "name2", 2L, "user")
        )
        userDao.insertAll(userList)
        val readResponse = userDao.getAll()
        Assert.assertEquals(userList, readResponse)
    }

    @Test
    fun saveUserAndCleanDatabase() = runTest {
        val userList = listOf(
            UserEntity("img", "name", 1L, "user"),
            UserEntity("img2", "name2", 2L, "user")
        )
        userDao.insertAll(userList)
        userDao.deleteAll()
        val readResponse = userDao.getAll()
        Assert.assertEquals(readResponse?.size, 0)
    }

    @Test
    fun saveUserReplaceConflict() = runTest {
        val expectedResult = listOf(UserEntity("img2", "name2", 1L, "user"))
        val userList = listOf(
            UserEntity("img", "name", 1L, "user"),
            UserEntity("img2", "name2", 1L, "user")
        )
        userDao.insertAll(userList)
        val readResponse = userDao.getAll()
        Assert.assertEquals(expectedResult, readResponse)
    }
}
