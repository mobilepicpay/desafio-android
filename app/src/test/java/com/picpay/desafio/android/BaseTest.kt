package com.picpay.desafio.android

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

abstract class BaseTest {

    @Before
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    open fun tearDown() {
        unmockkAll()
    }

}