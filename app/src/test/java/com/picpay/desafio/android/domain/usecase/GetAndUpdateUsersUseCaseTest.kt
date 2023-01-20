package com.picpay.desafio.android.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.UsersStub.listUsers
import com.picpay.desafio.android.UsersStub.listUsersEntity
import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetAndUpdateUsersUseCaseTest {

    private val repository: UserRepository = mock()
    private val getAndUpdateUsersUseCase = GetAndUpdateUsersUseCase(repository)

    private val exception = RuntimeException("dummy")

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should return Success `() =
        runTest {
            whenever(repository.getUpDateUsers()).thenReturn(
                flow {
                    emit(listUsersEntity)
                }
            )
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
            whenever(repository.getUpDateUsers()).thenThrow(
                exception
            )
            getAndUpdateUsersUseCase.invoke().catch {
                assertEquals(exception, it)
            }
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should emit HttpException`() =
        runTest {
            whenever(repository.getUpDateUsers()).thenReturn(
                flow {
                    throw HttpException(
                        Response.error<ResponseBody>(
                            500,
                            ResponseBody.create(
                                "".toMediaTypeOrNull(),
                                "some content"
                            )
                        )
                    )
                }
            )
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
            whenever(repository.getUpDateUsers()).thenReturn(
                flow {
                    throw exception
                }
            )

            val response = getAndUpdateUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Error(DataError(0, "dummy"))
                ),
                response
            )
        }
}
