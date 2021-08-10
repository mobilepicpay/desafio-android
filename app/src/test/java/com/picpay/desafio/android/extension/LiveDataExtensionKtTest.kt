package com.picpay.desafio.android.extension

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LiveDataExtensionKtTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `Should update live data value with async update`() {
        val oldData = LiveDataTestClass("old")
        val liveData = MutableLiveData(oldData)

        liveData.updateAsync { copy(text = "new") }

        val expected = LiveDataTestClass("new")
        liveData.observeForever {
            assertEquals(expected, it)
        }
    }

    @Test
    fun `Should do nothing when updating live data with no value`() {
        val liveData = MutableLiveData<LiveDataTestClass>()

        liveData.updateAsync { copy(text = "new") }

        assertNull(liveData.value)
    }

    private data class LiveDataTestClass(val text: String)
}