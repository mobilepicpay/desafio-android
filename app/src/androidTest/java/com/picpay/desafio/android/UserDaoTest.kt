package com.picpay.desafio.android

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = db.userDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun shouldInsertAndRetrieveUsers_whenUsersAreStored() = runBlocking {
        val users = listOf(
            User(1, "https://example.com/img1.png", "User One", "userone"),
            User(2, "https://example.com/img2.png", "User Two", "usertwo")
        )

        dao.insertUsers(users)
        val result = dao.getAllUsers().first()

        assertEquals(users, result)
    }

    @Test
    fun shouldReplaceUsers_whenSameIdIsInserted() = runBlocking {
        val user1 = User(1, "https://example.com/img1.png", "User One", "userone")
        val user2 = User(1, "https://example.com/img2.png", "Updated User", "updateduser")

        dao.insertUsers(listOf(user1))
        dao.insertUsers(listOf(user2))

        val result = dao.getAllUsers().first()
        Assert.assertEquals(1, result.size)
        Assert.assertEquals(user2, result[0])
    }

    @Test
    fun shouldReturnEmptyList_whenDatabaseIsEmpty() = runBlocking {
        val result = dao.getAllUsers().first()
        assertEquals(emptyList<User>(), result)
    }
}
