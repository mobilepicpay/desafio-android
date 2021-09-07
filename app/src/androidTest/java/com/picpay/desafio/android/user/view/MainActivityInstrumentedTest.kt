package com.picpay.desafio.android.user.view

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.R
import com.picpay.desafio.android.shared.extensions.checkRecyclerViewHasItems
import com.picpay.desafio.android.shared.extensions.click
import com.picpay.desafio.android.shared.extensions.matchesTextAtPosition
import com.picpay.desafio.android.user.helper.MockWebServerRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @Test
    fun shouldDisplayListItemWithCorrectlyData() {
        launchActivity<MainActivity>().apply {
            click(R.id.titleScreenTextView)
            //Sometimes the test fails without this click. Test was finishing before list appears.

            matchesTextAtPosition(R.id.recyclerView, R.id.username, 0, "@eduardo.santos")
            matchesTextAtPosition(R.id.recyclerView, R.id.name, 0, "Eduardo Santos")
            checkRecyclerViewHasItems(R.id.recyclerView, 1)
        }
    }
}