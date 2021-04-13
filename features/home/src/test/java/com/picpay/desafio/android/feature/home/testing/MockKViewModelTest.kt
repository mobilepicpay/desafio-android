package com.picpay.desafio.android.feature.home.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.shared.coroutine.CoroutineViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class MockKViewModelTest<VM : CoroutineViewModel<STATE, EVENT>, STATE, EVENT> {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    protected val states: ArrayList<STATE> = arrayListOf()

    private val stateObserver: Observer<STATE> = Observer { it?.let { states.add(it) } }

    protected val events: ArrayList<EVENT> = arrayListOf()

    private val eventObserver: Observer<EVENT> = Observer { it?.let { events.add(it) } }

    protected val dispatching = TestCoroutineService()

    @InjectMockKs
    protected lateinit var viewModel: VM

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel.state.observeForever(stateObserver)
        viewModel.event.observeForever(eventObserver)

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