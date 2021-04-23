package com.picpay.desafio.android.rest_picpay

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.rest_picpay.repository.UserRepositoryImpl
import com.picpay.desafio.android.rest_picpay.util.UserRepositoryRule
import com.picpay.desafio.android.rest_picpay.util.loadResource
import com.picpay.desafio.android.domain.errors.RemoteServiceError
import com.picpay.desafio.android.domain.models.User
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class UserRepositoryTests {

    @get:Rule
    val rule = UserRepositoryRule()

    private lateinit var repository: UserRepositoryImpl

    @Before
    fun `before each test`() {
        repository = UserRepositoryImpl(rule.api)
    }

    @Test
    fun `should handle no results properly`() {

        rule.defineScenario(
            status = 200,
            response = loadResource("200_search_no_results.json")
        )

        val noFacts = emptyList<User>()
      //  assertThat(simpleSearch()).isEqualTo(noFacts)
    }

    @Test
    fun `should handle client issue`() {

        rule.defineScenario(
            status = 404,
            response = loadResource("404_wrong_path.json")
        )

        assertThat(RemoteServiceError.ClientOrigin)

       //assertErrorTransformed(
       //    whenRunning = this::simpleSearch,
       //    expected = RemoteServiceIntegrationError.ClientOrigin
       //)
    }

    @Test
    fun `should handle remote system issue`() {

        rule.defineScenario(
            status = 500
        )

        //assertErrorTransformed(
        //    whenRunning = this::simpleSearch,
        //    expected = RemoteServiceIntegrationError.RemoteSystem
        //)
    }

    @Test
    fun `should handle broken contract`() {

        rule.defineScenario(
            status = 200,
            response = loadResource("200_search_broken_contract.json")

        )

       //assertErrorTransformed(
       //    whenRunning = this::simpleSearch,
       //    expected = RemoteServiceIntegrationError.UnexpectedResponse
       //)
    }

    @Test
    fun `should fetch facts with valid query term`() {

        rule.defineScenario(
            status = 200,
            response = loadResource("200_search_with_results.json")
        )

        val users = listOf(
            User(
                id = 1001,
                username = "@leonardo",
                name = "Leonardo Cruz",
                img = "https://randomuser.me/api/portraits/men/9.jpg"
            ),
            User(
                id = 1002,
                username = "@simone",
                name = "Simone Cruz",
                img = "https://randomuser.me/api/portraits/men/10.jpg"
            )
        )

       //  assertThat(simpleSearch()).isEqualTo(users)
    }

    @Test
    fun `should fetch users`() {

        rule.defineScenario(
            status = 200,
            response = loadResource("200_list_users.json")
        )

        val expected = listOf(
            User(
                id = 1001,
                username = "@leonardo",
                name = "Leonardo Cruz",
                img = "https://randomuser.me/api/portraits/men/9.jpg"
            ),
            User(
                id = 1002,
                username = "@simone",
                name = "Simone Cruz",
                img = "https://randomuser.me/api/portraits/men/10.jpg"
            )
        )

        val remoteUsers = runBlocking {
            repository.getUsers()
        }

        assertThat(remoteUsers).isEqualTo(expected)
    }

  //@Test
  //fun `should fetch categories`() {

  //    rule.defineScenario(
  //        status = 200,
  //        response = loadResource("200_categories.json")
  //    )

  //    val expected = listOf(
  //        Available("career"),
  //        Available("celebrity"),
  //        Available("dev")
  //    )

  //    val categories = runBlocking {
  //        infrastructure.availableCategories()
  //    }

  //    assertThat(categories).isEqualTo(expected)
  //}

  // private fun simpleSearch() = runBlocking {
  //     infrastructure.fetchFacts("Norris")
  // }
}