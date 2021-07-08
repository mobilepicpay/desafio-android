package com.picpay.desafio.android.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.domain.FetchUseCase
import com.picpay.desafio.android.domain.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FetchUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: FetchUseCase

    private val service: PicPayService = mockk(relaxed = true)


    @Before
    fun setUp() {
        subject = FetchUseCase(service)
    }

    @Test
    fun `given execute call when success response then should return result success`() {
        coEvery {
            service.getUsers()
        } coAnswers {
            mockk(relaxed = true)
        }

        val result = runBlocking {
            subject.execute()
        }

        assert(result is Result.Success)
    }

    @Test
    fun `given execute call when success response then should return result error`() {
        coEvery {
            service.getUsers()
        } coAnswers {
            throw Exception()
        }

        val result = runBlocking {
            subject.execute()
        }

        assert(result is Result.Error)
    }
}