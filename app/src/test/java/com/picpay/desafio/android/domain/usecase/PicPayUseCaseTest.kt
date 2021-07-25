package com.picpay.desafio.android.domain.usecase

import com.google.common.truth.Truth
import com.picpay.desafio.android.data.PicPayRepositoryImpl
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.source.local.UserDb
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PicPayUseCaseTest {

    private val repo = mockk<PicPayRepositoryImpl>(relaxed = true)

    @Test
    fun `when useCase get users, then check status loading and success`() = runBlockingTest {

        every {
            repo.getUsers()
        } returns flow<Resource<List<UserDb>>> {
            emit(Resource.Loading())
            emit(Resource.Success(emptyList()))
        }

        val useCase = PicPayUseCase(repository = repo)
        val resources = useCase.invoke().toList()
        Truth.assertThat(resources.size).isEqualTo(2)
        Truth.assertThat(resources[0] is Resource.Loading).isTrue()
        Truth.assertThat(resources[1] is Resource.Success).isTrue()
    }

    @Test
    fun `when useCase get users and an error occurs, then check status loading and error`() =
        runBlockingTest {

            every {
                repo.getUsers()
            } returns flow<Resource<List<UserDb>>> {
                emit(Resource.Loading())
                emit(Resource.Error(Exception("this is a test")))
            }

            val useCase = PicPayUseCase(repository = repo)
            val resources = useCase.invoke().toList()
            Truth.assertThat(resources.size).isEqualTo(2)
            Truth.assertThat(resources[0] is Resource.Loading).isTrue()
            Truth.assertThat(resources[1] is Resource.Error).isTrue()
        }

    @Test
    fun `when useCase get users and an error occurs, then check cache data`() =
        runBlockingTest {
            val listDb = listOf(UserDb(1, "url", "alessandro", "ale"))

            every {
                repo.getUsers()
            } returns flow<Resource<List<UserDb>>> {
                emit(Resource.Error(throwable = Exception("error connection"), data = listDb))
            }

            val useCase = PicPayUseCase(repository = repo)
            useCase.invoke().collect { resource ->
                Truth.assertThat(resource is Resource.Error).isTrue()
                Truth.assertThat(resource.data).isEqualTo(listDb)
            }
        }


}