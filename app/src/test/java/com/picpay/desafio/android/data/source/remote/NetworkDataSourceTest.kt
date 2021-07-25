package com.picpay.desafio.android.data.source.remote

import com.google.common.truth.Truth
import com.picpay.desafio.android.data.dto.UserResponse
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class NetworkDataSourceTest {

    private val api = spyk<PicPayApi>()

    @Test
    fun `when api response ok then data source must list it`() = runBlockingTest {
        val listResponse = listOf(UserResponse("url", "alessandro", 1, "ale"))
        coEvery { api.getUsers() } returns listResponse

        val dataSource = NetworkDataSource(api)
        val listSource = dataSource.allUsers()

        Truth.assertThat(listResponse).isEqualTo(listSource)
    }

    @Test
    fun `when api response null then data source must list it`() = runBlockingTest {
        val dataSource = NetworkDataSource(api)
        val listSource = dataSource.allUsers()
        Truth.assertThat(listSource).isNull()
    }

}