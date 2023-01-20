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
class GetUsersUseCaseTest {

    private val repository: UserRepository = mockk()
    private val getUsersUseCase = GetUsersUseCase(repository)

    private val exception = RuntimeException("dummy")

    @Test
    fun `Given getUsersUseCase When call invoke than should return Success and Success`() =
        runTest {
            coEvery { repository.getUser() } returns flow {
                emit(listUsersEntity)
                emit(listUsersEntity)
            }

            val response = getUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Success(listUsers),
                    Outcome.Success(listUsers)
                ),
                response
            )
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should return Error`() =
        runTest {
            coEvery { repository.getUser() } throws exception

            getUsersUseCase.invoke().catch {
                assertEquals(exception, it)
            }
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should emit HttpException`() =
        runTest {
            coEvery { repository.getUser() } returns flow {
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

            val response = getUsersUseCase.invoke().toList()

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
            coEvery { repository.getUser() } returns flow { throw exception }

            val response = getUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Error(DataError(0, "dummy"))
                ),
                response
            )
        }
}
