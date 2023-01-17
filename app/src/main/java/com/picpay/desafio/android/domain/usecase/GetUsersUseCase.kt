package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.core.FlowUseCase
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.mapper.UserMapper
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsersUseCase constructor(
    private val postsRepository: UserRepository
) : FlowUseCase<Outcome<List<User>>?>() {

    override fun performAction(): Flow<Outcome<List<User>>?> {
        return postsRepository.getUser().map {
            return@map when (it) {
                is Outcome.Success -> {
                    Outcome.Success(UserMapper.transformToList(it.data))
                }
                is Outcome.Error -> Outcome.Error(it.error)
                is Outcome.Loading -> Outcome.Loading(null)
                else -> {
                    null
                }
            }
        }
    }
}
