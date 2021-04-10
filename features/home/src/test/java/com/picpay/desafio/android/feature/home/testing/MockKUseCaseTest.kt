package com.picpay.desafio.android.feature.home.testing

import io.mockk.MockKAnnotations
import org.junit.After
import org.junit.Before

abstract class MockKUseCaseTest {

    protected val dispatching = TestDispatching()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        beforeTest()
    }

    @After
    fun tearDown() {
        afterTest()

        dispatching.clean()
    }

    open fun beforeTest() {
        // Override when needed
    }

    open fun afterTest() {
        // Override when needed
    }
}