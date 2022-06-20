package com.picpay.desafio.feature.contactlist.datasource.internal

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
internal class ContactListDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ContactListDatabase
    private lateinit var useDao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, ContactListDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        useDao = database.userDao()
    }

    @Test
    fun insertUserAndGetAllUser() = runTest {
        val userEntity = UserEntity(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )

        useDao.insertAll(userEntity)
        val actual = useDao.getAllUser()

        Truth.assertThat(actual).contains(userEntity)
    }

    @After
    fun closeDb() {
        database.close()
    }
}