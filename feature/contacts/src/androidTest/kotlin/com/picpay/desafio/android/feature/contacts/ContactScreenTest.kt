package com.picpay.desafio.android.feature.contacts

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.picpay.desafio.android.core.data.network.test.TestNetworkMonitor
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserModelList
import com.picpay.desafio.android.core.testing.DesafioAppKoinTestRule
import com.picpay.desafio.android.feature.contacts.di.test.testingFeatureContactsModule
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenUiState
import com.picpay.desafio.android.feature.contacts.viewmodel.SearchUiState
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ContactScreenTest {

    @get:Rule
    var rule = ContactsScreenComposeRule()

    private lateinit var networkMonitor: TestNetworkMonitor

    @Before
    fun setUp() {
        networkMonitor = TestNetworkMonitor()
    }

    private val titleMatcher by lazy {
        hasText(
            rule.compose.activity.resources
                .getString(R.string.contacts_screen_list_header)
        )
    }
    @Test
    fun testContactScreen_displaysTitleInitially() {
        rule.compose.setContent {
            ContactScreen({})
        }

        rule.compose
            .onNode(titleMatcher)
            .assertIsDisplayed()

    }

    private val searchFieldMatcher by lazy {
        hasText(
            rule.compose.activity.resources
                .getString(R.string.contacts_searchfield_placeholder)
        )
    }
    @Test
    fun testContactScreen_initialState_shouldDisplay_SearchField() {
        rule.compose.setContent {
            ContactScreen({})
        }

        rule.compose
            .onNode(searchFieldMatcher)
            .assertIsDisplayed()

    }

    private val noInternetMatcher by lazy {
        hasText(
            rule.compose.activity.resources
                .getString(R.string.contacts_searchfield_placeholder)
        )
    }
    @Test
    fun testContactScreen_noInternet_And_userListIsEmpty_shouldDisplay_noInternet() = runTest {
        // Given
        val uiState = ContactsScreenUiState(
            filteredUsers = emptyList(),
            isNetworkAvailable = false
        )

        //When
        rule.compose.setContent {
            ContactsScreenContent(
                uiState = uiState,
                launchGame = { },
                onEvent = { }
            )
        }

        backgroundScope.launch {
            networkMonitor.setConnected(false)


            //Then
            rule.compose
                .onNode(noInternetMatcher)
                .assertIsDisplayed()
        }
    }

    private val progressIndicatorMatcher by lazy {
        hasText(
            rule.compose.activity.resources
                .getString(com.picpay.desafio.android.core.design.R.string.circularprogressindicator_text)
        )
    }
    @Test
    fun testContactScreen_isSyncing_And_userListIsEmpty_shouldDisplay_progressIndicator() {
        // Given
        val uiState = ContactsScreenUiState(
            filteredUsers = emptyList(),
            isNetworkAvailable = true,
            isSyncing = true
        )

        //When
        rule.compose.setContent {
            ContactsScreenContent(
                uiState = uiState,
                launchGame = { },
                onEvent = { }
            )
        }

        //Then
        rule.compose
            .onNode(progressIndicatorMatcher)
            .assertIsDisplayed()
    }

    private val noResultsOnSearchMatcher by lazy {
        hasText(
            rule.compose.activity.resources
                .getString(com.picpay.desafio.android.core.design.R.string.no_results_sorry)
        )
    }
    @Test
    fun testContactScreen_hasInternet_searchIsNotEmpty_And_userListIsEmpty_shouldDisplay_noResults() {
        // Given
        val uiState = ContactsScreenUiState(
            users = fakeUserModelList,
            filteredUsers = emptyList(), // assuming search would make the list empty
            isNetworkAvailable = true,
            isSyncing = false,
            searchUiState = SearchUiState(searchQuery = "bla bla bla")
        )

        //When
        rule.compose.setContent {
            ContactsScreenContent(
                uiState = uiState,
                launchGame = { },
                onEvent = { }
            )
        }

        //Then
        rule.compose
            .onNode(noResultsOnSearchMatcher)
            .assertIsDisplayed()
    }

    private val contactListLastItemMatcher by lazy {
        hasText(
            rule.compose.activity.resources
                .getString(R.string.all_items_are_loaded)
        )
    }
    @Test
    fun testContactScreen_initialState_hasInternet_And_userListIsNotEmpty_shouldDisplay_contactList() {
        // Given
        val uiState = ContactsScreenUiState(
            filteredUsers = fakeUserModelList,
            isNetworkAvailable = true,
            isSyncing = false,
        )

        //When
        rule.compose.setContent {
            ContactsScreenContent(
                uiState = uiState,
                launchGame = { },
                onEvent = { }
            )
        }

        //Then
        rule.compose
            .onNode(contactListLastItemMatcher)
            .assertIsDisplayed()
    }
}

class ContactsScreenComposeRule : TestRule {

    @JvmField
    @Rule(order = 0)
    var testRule: DesafioAppKoinTestRule =
        DesafioAppKoinTestRule(listOf(testingFeatureContactsModule))

    @JvmField
    @Rule(order = 1)
    var compose: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
        createAndroidComposeRule<ComponentActivity>()


    override fun apply(base: Statement, description: Description?): Statement {
        return if (description != null) {
            var statement: Statement = compose.apply(base, description)
            statement = testRule.apply(statement, description)
            statement
        } else {
            base
        }
    }
}

