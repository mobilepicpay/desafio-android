package com.picpay.desafio.android

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.espresso.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseAndroidTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<SplashActivity> = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun before() {
        IdlingResource.register()
    }

    @After
    fun after() {
        IdlingResource.unregister()
    }
}