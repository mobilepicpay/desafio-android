package com.picpay.desafio.android.feature.home.interactor.user

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.feature.home.testing.MockKCoroutinesTest
import com.picpay.desafio.android.shared.data.local.PicPayLocalDataSource
import com.picpay.desafio.android.shared.data.local.UserLocal
import com.picpay.desafio.android.shared.data.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.shared.data.remote.UserRemote
import com.picpay.desafio.android.shared.exception.NoInternetException
import com.picpay.desafio.android.shared.exception.UnknowServerException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.HttpException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class DefaultUserGatewayTest : MockKCoroutinesTest() {

    @MockK
    private lateinit var localSource: PicPayLocalDataSource

    @MockK
    private lateinit var remoteSource: PicPayRemoteDataSource

    @InjectMockKs
    private lateinit var userGateway: DefaultUserGateway

    @Test
    fun `userGateway SHOULD extend UserGateway`() = runBlockingTest {
        assertThat(userGateway).isInstanceOf(UserGateway::class.java)
    }

    @Test
    fun `getUserList SHOULD return from LocalDataSource WHEN local is not empty`() = runBlockingTest {
        // Given
        val localList = arrayListOf(
            UserLocal(1, "Spinel", "Andre", "url"),
            UserLocal(2, "CAFU", "Pedro", "url")
        )
        val expectedEntityList = arrayListOf(
            UserEntity(1, "Spinel", "Andre", "url"),
            UserEntity(2, "CAFU", "Pedro", "url"),
        )
        coEvery { localSource.getUserList() } returns localList
        // When
        val result = userGateway.getUserList()
        // Then
        assertThat(result).hasSize(2)
        assertThat(result).containsExactlyElementsIn(expectedEntityList)
    }

    @Test
    fun `getUserList SHOULD return from RemoteDataSource WHEN local is empty`() = runBlockingTest {
        // Given
        coEvery { localSource.getUserList() } returns emptyList()
        coEvery { localSource.insertAll(any()) } returns Unit

        val remoteList = arrayListOf(
            UserRemote(1, "Spinel", "Andre", "url"),
            UserRemote(2, "CAFU", "Pedro", "url")
        )
        val expectedEntityList = arrayListOf(
            UserEntity(1, "Spinel", "Andre", "url"),
            UserEntity(2, "CAFU", "Pedro", "url"),
        )
        coEvery { remoteSource.getUserList() } returns remoteList
        // When
        val result = userGateway.getUserList()
        // Then
        assertThat(result).hasSize(2)
        assertThat(result).containsExactlyElementsIn(expectedEntityList)
    }

    @Test
    fun `getUserList SHOULD call localDataSource insertAll WHEN remote returns any list`() = runBlockingTest {
        // Given
        coEvery { localSource.getUserList() } returns emptyList()
        coEvery { localSource.insertAll(any()) } returns Unit

        val remoteList = arrayListOf(
            UserRemote(1, "Spinel", "Andre", "url"),
            UserRemote(2, "CAFU", "Pedro", "url")
        )
        val expectedLocal = arrayListOf(
            UserLocal(1, "Spinel", "Andre", "url"),
            UserLocal(2, "CAFU", "Pedro", "url"),
        )
        coEvery { remoteSource.getUserList() } returns remoteList
        // When
        userGateway.getUserList()
        // Then
        coVerify { localSource.insertAll(expectedLocal) }
    }

    @Test(expected = NoInternetException::class)
    fun `getUserList SHOULD throw NoInternetException WHEN catch UnknownHostException`() = runBlockingTest {
        // Given
        coEvery { localSource.getUserList() } returns emptyList()
        coEvery { remoteSource.getUserList() } throws UnknownHostException()
        // When
        userGateway.getUserList()
    }

    @Test(expected = UnknowServerException::class)
    fun `getUserList SHOULD throw UnknowServerException WHEN catch HttpException`() = runBlockingTest {
        // Given
        coEvery { localSource.getUserList() } returns emptyList()
        coEvery { remoteSource.getUserList() } throws HttpException(mockk(relaxed = true))
        // When
        userGateway.getUserList()
    }
}