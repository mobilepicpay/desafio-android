package com.picpay.desafio.android.domain.usescases

import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.UserCacheRepository
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchUsersUseCase(
    private val userRepositoryRemote: UserRepository,
    private val userCacheRepository: UserCacheRepository
) {

    suspend fun execute(): List<User> {
       return with(userCacheRepository) {
            if (cached().isNullOrEmpty()) {
                userRepositoryRemote.getUsers().apply { save(this) }
            } else cached().toList()
        }
    }
}