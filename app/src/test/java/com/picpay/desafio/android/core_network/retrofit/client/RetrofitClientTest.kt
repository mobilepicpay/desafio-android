package com.picpay.desafio.android.core_network.retrofit.client

import com.google.common.truth.Truth
import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.core_network.retrofit.client.RetrofitClient.makeCall
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.HttpException

class RetrofitClientTest {

    private val request: suspend () -> Response<Unit> = mockk()
    private val retrofitError: HttpException = mockk()

    @Test
    fun `Should return success response when request succeeds`() = runBlocking {
        coEvery { request() } returns Response.Success(Unit)

        val value: Flow<Response<Unit>> = flow {
            makeCall { request() }
        }

        Truth.assertThat(value.first()).isEqualTo(Response.Success(Unit))
    }

    @Test
    fun `Should return network error 500 when uncaught exception is thrown`() = runBlocking {
        coEvery { request() } throws object : Throwable() {}

        val value: Flow<Response<Unit>> = flow {
            makeCall { request() }
        }

        val error = value.first() as Response.Error

        Truth.assertThat(error.networkError.errorCode).isEqualTo(500)
        Truth.assertThat(error.networkError.errorMessage).isEqualTo(RetrofitClient.UNEXPECTED_ERROR)
    }

    @Test
    fun `Should return network error when a HTTP exception is thrown`() = runBlocking {
        coEvery { retrofitError.code() } returns 404
        coEvery { retrofitError.message() } returns "error"
        coEvery { request() } throws retrofitError

        val value: Flow<Response<Unit>> = flow {
            makeCall { request() }
        }
        val responseError = value.first() as Response.Error<Unit>

        Truth.assertThat(responseError.networkError.errorCode).isEqualTo(404)
        Truth.assertThat(responseError.networkError.errorMessage).isEqualTo("error")
    }

}
