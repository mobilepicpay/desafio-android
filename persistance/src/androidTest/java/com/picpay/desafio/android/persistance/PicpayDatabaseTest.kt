package com.picpay.desafio.android.persistance

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.persistance.dao.UserDao
import com.picpay.desafio.android.persistance.models.UserEntity
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PicpayDatabaseTest : TestCase() {
    //@get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var db: PicpayDatabase
    private lateinit var dao: UserDao

    //@get:Rule
    //var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PicpayDatabase::class.java)
            .build()
        dao = db.userDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertUsers() = runBlocking {
        val usersList: List<UserEntity> = listOf(
            UserEntity(
                "test1",
                "Leo",
                1,
                "@test1"
            )
        )
        dao.insertAll(usersList)

        val allUsers: List<UserEntity> = dao.getAllUsers()

        assertEquals(allUsers.size, 1)
    }

    @Test
    fun deleteUsers() = runBlocking {
        val usersList: List<UserEntity> = listOf(
            UserEntity(
                "test1",
                "Leo",
                1,
                "@test1"
            )
        )

        dao.insertAll(usersList)
        dao.deleteAllUsers()
        val allUsers: List<UserEntity> = dao.getAllUsers()

        assertEquals(allUsers.size, 0)
    }
}