package com.picpay.desafio.android.helper.rule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
open class CoroutineViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val mainTestDispatcher = TestCoroutineDispatcher()

    @Before
    fun setupCoroutine() {
        Dispatchers.setMain(mainTestDispatcher)
    }

    @After
    fun tearDownCoroutine() {
        mainTestDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}