package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.UsersStub.listUsers
import com.picpay.desafio.android.UsersStub.listUsersEntity
import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetAndUpdateUsersUseCaseTest {

    private val repository: UserRepository = mockk()
    private val getAndUpdateUsersUseCase = GetAndUpdateUsersUseCase(repository)

    private val exception = RuntimeException("dummy")

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should return Success `() =
        runTest {
            coEvery { repository.getUpDateUsers() } returns flow { emit(listUsersEntity) }

            val response = getAndUpdateUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Success(listUsers)
                ),
                response
            )
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should return Error`() =
        runTest {
            coEvery { repository.getUpDateUsers() } throws exception

            getAndUpdateUsersUseCase.invoke().catch {
                assertEquals(exception, it)
            }
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should emit HttpException`() =
        runTest {
            coEvery { repository.getUpDateUsers() } returns
                flow {
                    throw HttpException(
                        Response.error<ResponseBody>(
                            500,
                            ResponseBody.create("".toMediaTypeOrNull(), "some content")
                        )
                    )
                }

            val response = getAndUpdateUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Error(DataError(500, "HTTP 500 Response.error()"))
                ),
                response
            )
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should emit Exception`() =
        runTest {
            coEvery { repository.getUpDateUsers() } returns flow { throw exception }

            val response = getAndUpdateUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Error(DataError(0, "dummy"))
                ),
                response
            )
        }
}
