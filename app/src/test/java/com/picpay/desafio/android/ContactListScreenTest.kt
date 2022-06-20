package com.picpay.desafio.android

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import com.picpay.desafio.contact_list.presentation.ContactScreen
import com.picpay.desafio.contact_list.presentation.di.contactListPresentationModule
import com.picpay.desafio.feature.contactlist.usecase.GetUsersUseCase
import com.picpay.desafio.feature.contactlist.usecase.UserDomain
import com.picpay.desafio.ui.theme.DesafioandroidTheme
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.*
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@Config(instrumentedPackages = ["androidx.loader.content"])
@RunWith(RobolectricTestRunner::class)
class ContactListScreenTest : KoinTest {

    @get:Rule
    var composeRule = createComposeRule()

    private lateinit var mockedUseCaseModule: Module
    lateinit var mockedUseCase: GetUsersUseCase

    @Before
    fun setup() {
        mockedUseCase = mockk()
        mockedUseCaseModule = module(override = true) {
            single { mockedUseCase }
        }

        if (GlobalContext.getOrNull() == null) {
            startKoin {
                modules(mockedUseCaseModule, contactListPresentationModule)
            }
        }

        loadKoinModules(mockedUseCaseModule)
    }


    @Test
    fun verifyIfShowContactListWhenUseCaseReturnsSucess() {
        mockUseCaseResult()

        composeRule.setContent {
            DesafioandroidTheme {
                ContactScreen()
            }
        }

        composeRule.onNodeWithTag("ContactList").assertIsDisplayed()
        composeRule.onNodeWithTag("ContactListTitle").assertIsDisplayed()
        composeRule.onNodeWithTag("ContactListTitle").assertTextEquals("Contatos")
    }

    @Test
    fun verifyIfNotShowContactListAndTitleWhenUseCaseReturnsFailure() {
        mockUseCaseResult(true)

        composeRule.setContent {
            DesafioandroidTheme {
                ContactScreen()
            }
        }

        composeRule.onNodeWithTag("ContactListError").assertIsDisplayed()
            .assertTextEquals("Erro desconhecido, tente novamente mais tarde.")
        composeRule.onNodeWithTag("ContactList").assertDoesNotExist()
        composeRule.onNodeWithTag("ContactListTitle").assertDoesNotExist()
    }

    @Test
    fun verifyIfListMakeScroll() {
        mockUseCaseResult()

        composeRule.setContent {
            DesafioandroidTheme {
                ContactScreen()
            }
        }

        val itemList = composeRule.onNodeWithTag("ContactList")
            .performScrollToIndex(11)
            .onChildAt(1)

        val itemListName = itemList.onChildAt(2)
        val itemUserName = itemList.onChildAt(1)

        itemListName.assertTextEquals("José Maria")
        itemUserName.assertTextEquals("@JoseMaria")
    }

    @Test
    fun testChangeOrientationWithSaveState() {
        mockUseCaseResult()

        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val itemList = composeRule.onNodeWithTag("ContactList")
                    .performScrollToIndex(11)
                    .onChildAt(1)

                activity.recreate()

                val itemName = itemList.onChildAt(2)
                val itemUserName = itemList.onChildAt(1)

                itemName.assertTextEquals("José Maria")
                itemUserName.assertTextEquals("@JoseMaria")
            }
        }
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockedUseCaseModule)
        stopKoin()
    }

    private fun mockUseCaseResult(hasError: Boolean = false) {
        if (hasError) {
            coEvery { mockedUseCase() } returns Result.failure(Exception())
        } else {
            coEvery { mockedUseCase() } returns Result.success(
                listOf(
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Maria",
                        1,
                        "@JoseMaria"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                    UserDomain(
                        "https://randomuser.me/api/portraits/men/1.jpg",
                        "José Silva",
                        1,
                        "@JoseSilva"
                    ),
                )
            )
        }
    }
}