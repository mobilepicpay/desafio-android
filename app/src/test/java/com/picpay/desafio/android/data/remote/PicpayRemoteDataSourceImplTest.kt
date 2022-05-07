package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class PicpayRemoteDataSourceImplTest {

    private lateinit var datasource: PicpayRemoteDatasource
    private val api = mockk<PicpayApi>()

    @Before
    fun setUp() {
        datasource = PicpayRemoteDataSourceImpl(
            api = api
        )
    }

    @Test
    fun `should call api when get users is called`() = runBlocking {
        coEvery { api.getUsers() } returns listOf()

        /**
         * Cold flows (like flow builders used in the datasource impl) must have a collector
         * to have it's emit blocks consumed. That's why i used the first() function.
         * */
        datasource.getUsers().first()

        coVerify(exactly = 1) { api.getUsers() }
        confirmVerified()
    }

}
