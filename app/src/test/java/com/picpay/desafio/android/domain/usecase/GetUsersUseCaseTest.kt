package com.picpay.desafio.android.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.data.entity.UserEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val repository: UserRepository = Mockito.mock()
    private val getUsersUseCase = GetUsersUseCase(repository)

    private val listUsersEntity = listOf(
        UserEntity(img = "img 1", name = "name 1", username = "user name 1", id = 1L),
        UserEntity(img = "img 2", name = "name 2", username = "user name 2", id = 2L)
    )
    private val listUsers = listOf(
        User(img = "img 1", name = "name 1", username = "user name 1"),
        User(img = "img 2", name = "name 2", username = "user name 2")
    )

    private val exception = RuntimeException("dummy")

    @Test
    fun `Given getUsersUseCase When call invoke than should return Success and Success`() =
        runTest {
            whenever(repository.getUser()).thenReturn(
                flow {
                    emit(listUsersEntity)
                    emit(listUsersEntity)
                }
            )
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
            whenever(repository.getUser()).thenThrow(
                exception
            )
            getUsersUseCase.invoke().catch {
                assertEquals(exception, it)
            }
        }

    @Test
    fun `Given GetAndUpdateUsersUseCaseTest When call invoke than should emit HttpException`() =
        runTest {
            whenever(repository.getUser()).thenReturn(
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
            whenever(repository.getUser()).thenReturn(
                flow {
                    throw exception
                }
            )

            val response = getUsersUseCase.invoke().toList()

            assertEquals(
                listOf(
                    Outcome.Error(DataError(0, "dummy"))
                ),
                response
            )
        }
}
