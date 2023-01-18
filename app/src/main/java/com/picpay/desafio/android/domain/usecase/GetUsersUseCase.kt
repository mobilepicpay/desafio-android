package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.core.FlowUseCase
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.mapper.UserMapper
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException

class GetUsersUseCase constructor(
    private val postsRepository: UserRepository
) : FlowUseCase<Outcome<List<User>>>() {

    override fun performAction(): Flow<Outcome<List<User>>> =
        postsRepository.getUser().onStart {
            Outcome.Loading(null)
        }.map {
            Outcome.Success(UserMapper.transformToList(it))
        }.catch {
            if (it is HttpException) {
                Outcome.Error<List<User>>(DataError(it.code(), it.message))
            } else {
                Outcome.Error<List<User>>(DataError(0, it.message))
            }
        }
}
