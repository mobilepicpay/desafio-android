package com.picpay.desafio.android.network

import org.junit.Test
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class RetrofitFactoryTest {

    @Test
    fun `Should init retrofit factory correctly`() {
        val retrofit = RetrofitFactory.build("http://localhost:8080", TestApi::class.java)

        assertNotNull(retrofit)
        assertIs<TestApi>(retrofit)
    }

    private interface TestApi
}