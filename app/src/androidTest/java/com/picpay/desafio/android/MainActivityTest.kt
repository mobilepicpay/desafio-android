package com.picpay.desafio.android

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    val rule = activityScenarioRule<MainActivity>()

    var navControl: NavController? = null

    @Test
    fun shouldNavigateToHomeFragment() {
        val homeFragmentId = R.id.userListFragment

        rule.scenario.onActivity {
            navControl = Navigation.findNavController(it, R.id.nav_host_fragment_activity_main)
        }

        assertThat(navControl!!.currentDestination!!.id, `is`(equalTo(homeFragmentId)))
    }

}