package com.picpay.android.user

import com.picpay.android.user.usedatasoucer.network.PicPayUserService
import com.picpay.android.user.usedatasoucer.network.UserNetWorkRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class PicPayUserServiceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val picPayUserService: PicPayUserService = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getUsers is called then it should call service getUsers`() {

//        val sampleResponse = this::class.java.getResource("/users_mock").readText()

        coEvery { picPayUserService.getUsers() } returns Response.success(listOf())

        runBlockingTest {
            UserNetWorkRepository(picPayUserService, testDispatcher).getUsers()
        }

        coVerify { picPayUserService.getUsers() }

    }
}