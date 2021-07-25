package com.picpay.desafio.android.data

import com.google.common.truth.Truth
import com.picpay.desafio.android.data.source.local.LocalDataSource
import com.picpay.desafio.android.data.source.local.UserDb
import com.picpay.desafio.android.data.source.remote.NetworkDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PicPayRepositoryImplTest {

    private val localDataSource = mockk<LocalDataSource>(relaxed = true)
    private val remoteDataSource = mockk<NetworkDataSource>(relaxed = true)
    private lateinit var repo: PicPayRepositoryImpl

    @Before
    fun setup() {
        repo = PicPayRepositoryImpl(remoteData = remoteDataSource, localData = localDataSource)
    }

    @Test
    fun `when repo get users, then check first status is loading`() = runBlockingTest {
        coEvery {
            remoteDataSource.allUsers()
        } returns emptyList()

        coEvery {
            localDataSource.getAllUsers()
        } returns flow {
            emit(emptyList<UserDb>())
        }

        val shouldBeLoadingStep = repo.getUsers().first()
        Truth.assertThat(shouldBeLoadingStep is Resource.Loading).isTrue()
    }

    @Test
    fun `when repo get users, then check status loading and success`() = runBlockingTest {
        coEvery {
            remoteDataSource.allUsers()
        } returns emptyList()

        coEvery {
            localDataSource.getAllUsers()
        } returns flow {
            emit(emptyList<UserDb>())
        }

        val resources = repo.getUsers().toList()

        Truth.assertThat(resources.size).isEqualTo(2)
        Truth.assertThat(resources[0] is Resource.Loading).isTrue()
        Truth.assertThat(resources[1] is Resource.Success).isTrue()
    }

    @Test
    fun `when repo get users and an error occurs, then check status loading and error`() =
        runBlockingTest {
            coEvery {
                remoteDataSource.allUsers()
            } throws Exception("this is a test")

            coEvery {
                localDataSource.getAllUsers()
            } returns flow {
                emit(emptyList<UserDb>())
            }

            val resources = repo.getUsers().toList()

            Truth.assertThat(resources.size).isEqualTo(2)
            Truth.assertThat(resources[0] is Resource.Loading).isTrue()
            Truth.assertThat(resources[1] is Resource.Error).isTrue()
        }
}