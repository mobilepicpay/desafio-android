package com.picpay.desafio.android.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeUserRepository(
    private val shouldFail: Boolean = false,
    initialUsers: List<User> = emptyList()
) : UserRepository {

    private val _users = MutableStateFlow(initialUsers)
    override val users: Flow<List<User>> = _users.asStateFlow()

    override suspend fun refreshUsers() {
        if (shouldFail) {
            throw RuntimeException("Erro simulado ao tentar carregar os usuários.")
        }

        _users.value = listOf(
            User(1, "Maria Silva", "https://...", "@mariasilva"),
            User(2, "João Santos", "https://...", "@joaosantos")
        )
    }
}
