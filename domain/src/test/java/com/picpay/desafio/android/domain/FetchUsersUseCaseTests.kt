package com.picpay.desafio.android.domain

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.domain.errors.NetworkingError
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.UserCacheRepository
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usescases.FetchUsersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchUsersUseCaseTests {

    private lateinit var cachedUsers: UserCacheRepository
    private lateinit var remoteUsers: UserRepository

    private val users by lazy {
        listOf(
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
    }

    @Before
    fun `before each test`() {
    }

    @Test
    fun `should fetch from cache from available`() {
        runBlocking {
            // Given
            `given that remote service not available`()
            `given that cache returns users`()

            // When
            val usecase = FetchUsersUseCase(remoteUsers, cachedUsers)

            // Then
            assertThat(usecase.execute()).isEqualTo(users)
        }
    }

    @Test
    fun `should fetch from remote when cache absent`() {
        runBlocking {
            // Given
            `given that remote service returns users`()
            `given that cache is absent`()

            // When
            val usecase = FetchUsersUseCase(remoteUsers, cachedUsers)

            // Then
            assertThat(usecase.execute()).isEqualTo(users)
        }
    }

    private fun `given that cache is absent`() {
        cachedUsers = object : UserCacheRepository {
            override suspend fun save(users: List<User>) = Unit

            override suspend fun cached(): List<User> = emptyList()
        }
    }

    private fun `given that cache returns users`() {
        cachedUsers = object : UserCacheRepository {
            override suspend fun save(users: List<User>) = Unit

            override suspend fun cached(): List<User> = users
        }
    }

    private fun `given that remote service returns users`() {
        remoteUsers = object : UserRepository {
            override suspend fun getUsers(): List<User> = users
        }
    }

    private fun `given that remote service not available`() {
        remoteUsers = object : UserRepository {
            override suspend fun getUsers(): List<User> {
                throw NetworkingError.HostUnreachable
            }
        }
    }
}