package com.picpay.desafio.android.network

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SafeDataRequestKtTest {

    private val fakeApi = mockk<FakeApi>()
    private val fakeRepository = FakeRepository(fakeApi)

    @Test
    fun `Should return ResultWrapper as Success when fake api call succeeds`() = runBlockingTest {
        coEvery { fakeApi.doRequest() } returns Unit

        val response = fakeRepository.doRequest()

        response as ResultWrapper.Success<Unit>
        assertEquals(response.content, Unit)
    }

    @Test
    fun `Should return ResultWrapper as Error when fake api call fails`() = runBlockingTest {
        val throwable = Throwable("error")
        coEvery { fakeApi.doRequest() } throws throwable

        val response = fakeRepository.doRequest()

        response as ResultWrapper.Error
        assertEquals(response.throwable, throwable)
    }

    inner class FakeRepository(
        private val fakeApi: FakeApi
    ) {
        suspend fun doRequest(): ResultWrapper<Unit> {
            return safeDataRequest { fakeApi.doRequest() }
        }
    }

    interface FakeApi {
        suspend fun doRequest()
    }
}