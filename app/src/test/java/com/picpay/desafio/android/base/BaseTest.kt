package com.picpay.desafio.android.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseTest : TestCase() {

    @get: Rule
    val coroutineRule = CoroutineTestRule()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
    }

}