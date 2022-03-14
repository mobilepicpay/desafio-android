package com.picpay.desafio.android.repository.remote

import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.remote.webclient.PicPayService
import io.mockk.*
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSourceImpTest{
    private var picPayServiceMock: PicPayService = mockk()
    private val userRemoteDataSource: UserRemoteDataSource = spyk(UserRemoteDataSourceImp(picPayServiceMock))

    @Test
    fun must_return_success_when_api_return_success() {
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

        run { userRemoteDataSource.getUser(success, failure) }

        verify { userRemoteDataSource.getUser(capture(successSlot), failure) }
    }

    @Test
    fun must_return_failure_when_api_return_failure() {
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

        run { userRemoteDataSource.getUser(success = success, failure = failure) }

        verify { userRemoteDataSource.getUser(success = any(), failure = capture(failureSlot)) }
    }

    @Test
    fun must_devolve_failure_when_the_connection_with_the_api_failure(){
        val success : (List<User>) -> Unit = {}
        val failure : (String) -> Unit = {}
        val failureSlot = slot<(String) -> Unit>()
        failureSlot.captured = failure

        val callMock: Call<List<User>> = mockk()
        val throwableMock: Throwable = mockk()

        every { picPayServiceMock.getUsers() } returns callMock
        every { callMock.enqueue(any()) } answers {
            (args[0] as Callback<List<User>>).apply {
                onFailure(mockk(), throwableMock)
            }
        }

        throwableMock.apply {
            every { message } answers {""}
        }

        run { userRemoteDataSource.getUser(success, failure) }
        verify { userRemoteDataSource.getUser(any(), capture(failureSlot)) }

    }
}