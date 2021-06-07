package com.picpay.desafio.android.usecases

import com.picpay.desafio.android.entities.UsersDomain
import com.picpay.desafio.android.repository.UsersRepository
import com.picpay.desafio.android.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetUsersUseCases {
    fun getUsers(): Flow<ResultUsers>

    sealed class ResultUsers {
        data class UsersList(val list: List<UsersDomain>) : ResultUsers()
        object NoUsers : ResultUsers()
        object Error : ResultUsers()
    }
}

class GetUsersUseCasesImpl(
    private val repository: UsersRepository
) : GetUsersUseCases {

    override fun getUsers(): Flow<GetUsersUseCases.ResultUsers> {
        return repository.getUsers()
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        when {
                            it.result.isEmpty() ->
                                GetUsersUseCases.ResultUsers.NoUsers
                            else ->
                                GetUsersUseCases.ResultUsers.UsersList(it.result.reversed())
                        }
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        GetUsersUseCases.ResultUsers.Error
                    }
                }
            }
    }
}