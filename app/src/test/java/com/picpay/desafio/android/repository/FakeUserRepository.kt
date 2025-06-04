package com.picpay.desafio.android.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeUserRepository : UserRepository {

    var shouldReturnError = false
    var mockUsers: List<User> = listOf(
        User(1, "User 1", "img1.jpg", "@user1"),
        User(2, "User 2", "img2.jpg", "@user2")
    )
    var delayInMillis: Long = 0

    private val _users = MutableStateFlow<List<User>>(emptyList())
    override val users: Flow<List<User>> = _users.asStateFlow()

    override suspend fun refreshUsers() {
        if (shouldReturnError) {
            throw RuntimeException("Erro simulado no refresh")
        }
        if (delayInMillis > 0) {
            kotlinx.coroutines.delay(delayInMillis)
        }
        _users.value = mockUsers
    }

    fun clearData() {
        _users.value = emptyList()
    }
}