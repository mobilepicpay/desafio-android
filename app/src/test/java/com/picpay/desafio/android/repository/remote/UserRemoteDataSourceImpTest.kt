package com.picpay.desafio.android.repository.remote

import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.remote.webclient.PicPayService
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSourceImpTest{
    private var picPayServiceMock: PicPayService = mockk()
    private val userRemoteDataSource: UserRemoteDataSource = spyk(UserRemoteDataSourceImp(picPayServiceMock))

    @Test
    fun deve_usar_sucesso_quando_api_retornar_sucesso() {
        val success: (List<User>) -> Unit = {}
        val successSlot = slot<(List<User>) -> Unit>()
        val failure: (String) -> Unit = {}

        val callMock: Call<List<User>> = mockk()
        val responseMock: Response<List<User>> = mockk()

        every { picPayServiceMock.getUsers() } returns callMock
        every { callMock.enqueue(any()) } answers {
            (args[0] as Callback<List<User>>).apply {
                onResponse(mockk(), responseMock)
            }
        }

        responseMock.apply {
            every { isSuccessful } answers { true }
            every { body() } returns mockk()
        }

        runBlocking { userRemoteDataSource.getUser(success, failure) }

        verify { userRemoteDataSource.getUser(capture(successSlot), failure) }
    }

    @Test
    fun deve_notificar_falha_quando_api_retornar_falha() {
        val success: (List<User>) -> Unit = {}
        val failure: (String) -> Unit = {}
        val failureSlot = slot<(String) -> Unit>()
        failureSlot.captured = failure

        val callMock: Call<List<User>> = mockk()
        val responseMock: Response<List<User>> = mockk()

        every { picPayServiceMock.getUsers() } returns callMock
        every { callMock.enqueue(any()) } answers {
            (args[0] as Callback<List<User>>).apply {
                onResponse(mockk(), responseMock)
            }
        }

        responseMock.apply {
            every { isSuccessful } answers { false }
            every { message() } answers {""}
        }

        runBlocking { userRemoteDataSource.getUser(success = success, failure = failure) }

        verify { userRemoteDataSource.getUser(success = any(), failure = capture(failureSlot)) }
    }

    @Test
    fun deve_devolver_falha_quando_api_retornar_falha(){
        val success : (List<User>) -> Unit = {}
        val failure : (String) -> Unit = {}
        val failureSlot = slot<(String) -> Unit>()
        failureSlot.captured = failure

        val callMock: Call<List<User>> = mockk()
        val failureMock: Throwable = mockk()

        every { picPayServiceMock.getUsers() } returns callMock
        every { callMock.enqueue(any()) } answers {
            (args[0] as Callback<List<User>>).apply {
                onFailure(mockk(), failureMock)
            }
        }

        failureMock.apply {
            every { message } answers {""}
        }

        runBlocking { userRemoteDataSource.getUser(success, failure) }
        verify { userRemoteDataSource.getUser(any(), capture(failureSlot)) }

    }
}