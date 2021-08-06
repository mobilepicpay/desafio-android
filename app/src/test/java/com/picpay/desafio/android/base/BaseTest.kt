package com.picpay.desafio.android.base

import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class BaseTest {
    @Before
    open fun setUp(){
        MockitoAnnotations.openMocks(this)
    }
}