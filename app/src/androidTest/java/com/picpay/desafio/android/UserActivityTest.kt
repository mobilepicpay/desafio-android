package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.di.getTestModule
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.ui.UserActivity
import io.mockk.coEvery
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject


class UserActivityTest : KoinTest {

    private val mockRepository by inject<UserRepository>()

    @Before
    fun setUp() {
        loadKoinModules(
            getTestModule()
        )
    }


    @Test
    fun deveMostrarTitulo() {
        launchActivity<UserActivity>().apply {
            onView(withText(R.string.title))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun quandoRepositoryRetornarSucesso_DeveMostrarAPosicaoNaLista() {
        coEvery { mockRepository.getAllUser() } returns successfulResponse()

        launchActivity<UserActivity>().apply {
            onView(withId(R.id.recyclerView))
                .check(matches(hasChildCount(3)))
        }
    }

    @Test
    fun quandoRepositoryRetornarSucesso_DeveMostrarUsuariosCorretosNaLista() {
        coEvery { mockRepository.getAllUser() } returns successfulResponse()

        launchActivity<UserActivity>().apply {

            onView(withText("pessoa1"))
                .check(matches(isDisplayed()))

            onView(withText("Pessoa1"))
                .check(matches(isDisplayed()))

            onView(withText("pessoa2"))
                .check(matches(isDisplayed()))

            onView(withText("Pessoa2"))
                .check(matches(isDisplayed()))


            onView(withText("pessoa3"))
                .check(matches(isDisplayed()))

            onView(withText("Pessoa3"))
                .check(matches(isDisplayed()))

        }
    }


    @Test
    fun quandoRepositoryRetornarVazio_recylcerViewDeveSerVazio() {

        coEvery { mockRepository.getAllUser() } returns emptyList()

        launchActivity<UserActivity>().apply {
            onView(withId(R.id.recyclerView))
                .check(matches(hasChildCount(0)))
        }
    }

    @Test
    fun quandoRepositoryRetornarSucesso_DeveDesaparecerAProgressBar() {

        coEvery { mockRepository.getAllUser() } returns successfulResponse()

        launchActivity<UserActivity>().apply {
            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))

        }
    }

    @Test
    fun quandoRepositoryReornarVazio_DeveDesaparecerProgressBar() {

        coEvery { mockRepository.getAllUser() } returns emptyList()

        launchActivity<UserActivity>().apply {
            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }


    @Test
    fun quandoRepositoryTratarException_DeveDesaparecerProgressBar() {

        coEvery { mockRepository.getAllUser() } throws Exception()

        launchActivity<UserActivity>().apply {
            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }


    private fun successfulResponse() = listOf(
        User(
            id = 1,
            name = "Pessoa1",
            imgUrl = "https://test.com",
            userName = "pessoa1"
        ),
        User(
            id = 2,
            name = "Pessoa2",
            imgUrl = "https://test2.com",
            userName = "pessoa2"
        ),
        User(
            id = 3,
            name = "Pessoa3",
            imgUrl = "https://test3.com",
            userName = "pessoa3"
        ),
    )
}



