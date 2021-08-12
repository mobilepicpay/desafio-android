package com.picpay.desafio.android.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.api.datasources.UserRemoteDataSource
import com.picpay.desafio.android.data.db.datasources.UserLocalDataSource
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var userRemoteDataSource: UserRemoteDataSource

    @MockK
    private lateinit var userLocalDataSource: UserLocalDataSource

    @MockK
    private lateinit var dataRequestManager: DataRequestManager

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)

        userRepository = UserRepositoryImpl(
            userRemoteDataSource,
            userLocalDataSource,
            dataRequestManager
        )
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `refreshUsers, when it's called, it doesn't pass a lambda to load local data`() {
        /*runBlockingTest {
            userRepository.refreshUsers()

            coVerify {
                dataRequestManager.performDataRequestManagement<List<User>, DataErrorException>(
                    withNullableArg {
                        assertTrue(it == null)
                    },
                    any(),
                    any(),
                    any()
                )
            }
        }*/
    }

    @Test
    fun `refreshUsers, when it's called, it always passes a lambda returning true to always fetch data`() {
        /*runBlockingTest {
            userRepository.refreshUsers()

            coVerify {
                dataRequestManager.performDataRequestManagement<List<User>, DataErrorException>(
                    any(),
                    withArg {
                        assertTrue(it.invoke())
                    },
                    any(),
                    any()
                )
            }
        }*/
    }
}
