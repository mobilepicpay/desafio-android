package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.core.FlowUseCase
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.core.Params
import com.picpay.desafio.android.domain.mapper.UserMapper
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetUsersUseCase constructor(
    private val postsRepository: UserRepository
) : FlowUseCase<GetUsersUseCase.Request, List<User>> {

    data class Request(val isGetCacheValues: Boolean) : Params

    override fun invoke(params: Request): Flow<Outcome<List<User>>> = flow {
        postsRepository.getUser(params.isGetCacheValues).catch { error ->
            if (error is HttpException) {
                emit(Outcome.Error(DataError(error.code(), error.message)))
            } else {
                emit(Outcome.Error(DataError(0, error.message)))
            }
        }.collect {
            emit(Outcome.Success(UserMapper.transformToList(it)))
        }
    }
}
