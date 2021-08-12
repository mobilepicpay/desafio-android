package com.picpay.desafio.android.data.db.datasources

import android.database.sqlite.SQLiteAbortException
import android.database.sqlite.SQLiteDatabaseCorruptException
import com.picpay.desafio.android.data.db.dao.UserDao
import com.picpay.desafio.android.data.db.entities.UserEntity
import com.picpay.desafio.android.data.db.mappers.UserLocalMapper
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserLocalDataSourceImplTest {

    @MockK
    private lateinit var userLocalMapper: UserLocalMapper

    @MockK
    private lateinit var userDao: UserDao

    private lateinit var userLocalDataSource: UserLocalDataSource

    private val users = listOf(
        User(1, "John", "image url", "John10"),
        User(2, "Ana", "image url", "Ana07")
    )

    private val userEntities = listOf(
        UserEntity(1, "John", "image url", "John10"),
        UserEntity(2, "Ana", "image url", "Ana07")
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)

        userLocalDataSource = UserLocalDataSourceImpl(userLocalMapper, userDao)
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `insertUsers, when it succeeds, it inserts all the users`() {
        every { userLocalMapper.fromModelToEntity(users.first()) }.returns(userEntities.first())
        every { userLocalMapper.fromModelToEntity(users.last()) }.returns(userEntities.last())

        runBlockingTest {
            userLocalDataSource.insertUsers(users)

            coVerify {
                userDao.insert(
                    withArg {
                        assertTrue(it.size == 2)
                        assertTrue(it.contains(userEntities.first()))
                        assertTrue(it.contains(userEntities.last()))
                    }
                )
            }
        }
    }

    @Test(expected = DataErrorException.DatabaseErrorException::class)
    fun `insertUsers, when it fails, it throws a database exception`() {
        coEvery { userDao.insert(any()) }.throws(SQLiteDatabaseCorruptException())

        runBlockingTest {
            userLocalDataSource.insertUsers(users)
        }
    }

    @Test
    fun `getUsers, when it succeeds, it returns all the users that are in database`() {
        coEvery { userDao.getAll() }.returns(userEntities)

        every { userLocalMapper.fromEntityToModel(userEntities.first()) }.returns(users.first())
        every { userLocalMapper.fromEntityToModel(userEntities.last()) }.returns(users.last())

        runBlockingTest {
            val users = userLocalDataSource.getUsers()

            assertTrue(users.size == 2)
            assertTrue(users.contains(users.first()))
            assertTrue(users.contains(users.last()))
        }
    }

    @Test(expected = DataErrorException.DatabaseErrorException::class)
    fun `getUsers, when it fails, it throws a database exception`() {
        coEvery { userDao.getAll() }.throws(SQLiteAbortException())

        runBlockingTest {
            userLocalDataSource.getUsers()
        }
    }
}
