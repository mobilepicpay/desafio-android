package com.picpay.desafio.android.feature.home.interactor.user

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.feature.home.testing.MockKUseCaseTest
import com.picpay.desafio.android.shared.domain.EntityResult
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUserListUseCaseTest : MockKUseCaseTest() {

    @MockK
    private lateinit var userGateway: UserGateway

    @InjectMockKs
    private lateinit var useCase: GetUserListUseCase

    @Test
    fun `useCase SHOULD return Success`() = runBlockingTest {
        // Given
        coEvery { userGateway.getUserList() } returns arrayListOf()
        // When
        val result = useCase()
        // Then
        assertThat(result).isInstanceOf(EntityResult.Success::class.java)
    }
}