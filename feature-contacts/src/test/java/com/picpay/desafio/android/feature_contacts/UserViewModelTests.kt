package com.picpay.desafio.android.feature_contacts


import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.domain.errors.RemoteServiceError
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.UserCacheRepository
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usescases.FetchUsersUseCase
import com.picpay.desafio.android.feature_contacts.mappers.toListPresentation
import com.picpay.desafio.android.feature_contacts.models.UserPresentation
import com.picpay.desafio.android.feature_contacts.presentation.UserInteraction
import com.picpay.desafio.android.feature_contacts.presentation.UserViewModel
import com.picpay.desafio.android.feature_contacts.presentation.UsersScreenState
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import kotlin.time.ExperimentalTime

class UserViewModelTests {

    @get:Rule val helper = CoroutinesTestHelper()

    private lateinit var viewModel: UserViewModel
    private lateinit var usersService: FakeRemoteUserServices

    class FakeRemoteUserServices(var withResults: Boolean = true) : UserRepository {
        private val users by lazy {
            listOf(
                User(
                    id = 1,
                    username = "@leonardo",
                    name = "leonardo",
                    img = "https://api.chucknorris.io/jokes/2wzginmks8azrbaxnamxdw"
                ),
                User(
                    id = 2,
                    username = "@simone",
                    name = "simone",
                    img = "https://api.chucknorris.io/jokes/2wzginmks8azrbaxnamxdw"
                )
            )
        }

        private val error by lazy {
            RemoteServiceError.UnexpectedResponse
        }

        override suspend fun getUsers(): List<User> =
            if (withResults) users else throw error
    }

    object FakeCategoriesCacheService : UserCacheRepository {
        override suspend fun save(users: List<User>) = Unit

        override suspend fun cached(): List<User> = emptyList()
    }


    @Before
    fun `before each test`() {
        usersService = FakeRemoteUserServices()
        val fetchFacts = FetchUsersUseCase(usersService, FakeCategoriesCacheService)

        viewModel = UserViewModel(fetchFacts)
    }

    @ExperimentalTime
    @Test
    fun `should report failure when fetching from remote`() {
        runBlocking {

            usersService.withResults = false

            viewModel.run {
                bind().test {
                    handle(UserInteraction.OpenedScreen)

                    val emissions = listOf(expectItem(), expectItem(), expectItem())
                    val viewStates = listOf(
                        UsersScreenState.Idle,
                        UsersScreenState.Loading,
                        UsersScreenState.Failed(RemoteServiceError.UnexpectedResponse)
                    )

                    assertThat(emissions).isEqualTo(viewStates)
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }
    }

    @ExperimentalTime
    @Test fun `should fetch users from remote data source with success`() {
        runBlocking {
            viewModel.run {
                bind().test {
                    handle(UserInteraction.OpenedScreen)

                    val presentation = listOf(
                        UserPresentation(
                                id = 1,
                                username = "@leonardo",
                                name = "leonardo",
                                img = "https://api.chucknorris.io/jokes/2wzginmks8azrbaxnamxdw"
                            ),
                        UserPresentation(
                                id = 2,
                                username = "@simone",
                                name = "simone",
                                img = "https://api.chucknorris.io/jokes/2wzginmks8azrbaxnamxdw"
                            )
                        )

                    val emissions = listOf(expectItem(), expectItem(), expectItem())
                    val viewStates = listOf(
                        UsersScreenState.Idle,
                        UsersScreenState.Loading,
                        UsersScreenState.Success(presentation)
                    )

                    assertThat(emissions).isEqualTo(viewStates)
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }
    }
}

class CoroutinesTestHelper : ExternalResource() {

    private val singleThread = newSingleThreadContext("Testing thread")
    val scope = CoroutineScope(singleThread)

    override fun before() {
        Dispatchers.setMain(singleThread)
        super.before()
    }

    override fun after() {
        Dispatchers.resetMain()
        singleThread.close()
        scope.cancel()
        super.after()
    }
}