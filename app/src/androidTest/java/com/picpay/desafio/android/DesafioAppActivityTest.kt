package com.picpay.desafio.android

import android.app.Activity
import androidx.test.core.app.launchActivity
import com.picpay.desafio.android.core.testing.DesafioAppKoinTestRule
import com.picpay.desafio.android.di.testingModules
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import kotlin.test.assertFalse

class DesafioAppActivityTest : KoinTest {

    private lateinit var activity: Activity

    @get:Rule
    val testRule = DesafioAppKoinTestRule(
        modules = listOf(testingModules)
    )

    @Test
    fun asserts_DecorFitsSystemWindows_isSet_to_False() {
        launchActivity<DesafioAppActivity>().use { scenario ->
            scenario.onActivity { activity ->
                assertFalse(activity.window.decorView.fitsSystemWindows)
            }
        }
    }
}