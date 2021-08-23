package com.picpay.desafio.android.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.picpay.desafio.android.local.config.DataBase
import com.picpay.desafio.android.local.dao.UserDAO
import com.picpay.desafio.android.model.entity.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class LocalTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DataBase
    private lateinit var dao: UserDAO

    @Before
    @Throws(IOException::class)
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
           ApplicationProvider.getApplicationContext(),
           DataBase::class.java
        ).build()
        dao = database.userDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        database.close()
    }

    @Test
    fun insetUser() = runBlocking {
        val user = UserEntity(
            id = 1,
            name = "Maria",
            img = "https://www.imagem.teste",
            username = "Maria Bonita"
        )
        dao.save(user)
        val userResult = dao.findUsersByName("Maria")
        assertThat(userResult.name, equalTo(user.name))
    }

    @Test
    fun insetListUsers() = runBlocking {
        val listUser = mutableListOf<UserEntity>()
        for (i in 1..3) {
            listUser.add(
                UserEntity(
                    id = i,
                    name = "User $i",
                    img = "https://www.imagem.teste.$i",
                    username = "User $i"
                )
            )
        }
        dao.saveAll(listUser)
        val list = dao.getUsers()
        assertThat(list.first(), equalTo(listUser))
    }

    @Test
    fun deleteUser() = runBlocking {
        val user = UserEntity(
            id = 1,
            name = "Maria",
            img = "https://www.imagem.teste",
            username = "Maria Bonita"
        )
        dao.save(user)
        dao.delete(user)
        val userResult = dao.findUsersByName("Maria")
        assertTrue(userResult?.name.isNullOrEmpty())
    }
}