package com.picpay.desafio.android.feature.home.interactor.user

import com.google.common.truth.Truth
import com.picpay.desafio.android.feature.home.testing.MockKCoroutinesTest
import com.picpay.desafio.android.shared.data.local.PicPayLocalDataSource
import com.picpay.desafio.android.shared.data.remote.PicPayRemoteDataSource
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

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
        Truth.assertThat(userGateway).isInstanceOf(UserGateway::class.java)
    }
}