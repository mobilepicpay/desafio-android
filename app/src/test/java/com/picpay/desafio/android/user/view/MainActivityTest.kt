package com.picpay.desafio.android.user.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.R
import com.picpay.desafio.android.application.NonInstrumentedTestApplication
import com.picpay.desafio.android.helper.MockKoinTest
import com.picpay.desafio.android.helper.checkRecyclerViewHasItems
import com.picpay.desafio.android.helper.matchIsVisible
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.repository.UserRepository
import io.mockk.coEvery
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.mock.declareMock
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(application = NonInstrumentedTestApplication::class)
class MainActivityTest : MockKoinTest() {

    private lateinit var repository: UserRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun `Set up`() {
        repository = declareMock()
    }

    @Test
    fun `Should show progress bar when screen opens`() {
        launchActivity<MainActivity>().onActivity {
            matchIsVisible(R.id.progressBar)
        }
    }

    @Test
    fun `Should show users list when repository succeeds`() {
        val users = listOf(
            UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username")
        )
        coEvery { repository.getUsers() } returns flowOf(users)

        launchActivity<MainActivity>().onActivity {
            checkRecyclerViewHasItems(R.id.recyclerView, 1)
        }
    }

    @Test
    fun `Should show toast with correct message when repository fails`() {
        val errorFlow = flow<List<UserDomain>> {
            throw Throwable("error")
        }
        coEvery { repository.getUsers() } returns errorFlow

        launchActivity<MainActivity>().onActivity {
            assertTrue(ShadowToast.showedToast("error"))
        }
    }
}