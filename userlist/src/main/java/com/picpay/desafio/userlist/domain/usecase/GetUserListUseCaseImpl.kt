package com.picpay.desafio.userlist.domain.usecase

import android.util.Log
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import kotlinx.coroutines.flow.*
import retrofit2.HttpException

class GetUserListUseCaseImpl(
    private val repository: UserListRepository
) : GetUserListUseCase() {
    override suspend fun execute(params: Unit): Flow<Resource<List<User>>> = flow {
        repository.getListFromServer()
            .onStart { this@flow.emit(Resource.loading<List<User>>()) }
            .catch {
                repository.getListFromDatabase()
                    .catch { this@flow.emit(Resource.error<List<User>>(it)) }
                    .collect { this@flow.emit(it) }
            }
            .collect {
                it.value?.let { newList -> repository.saveListFromServer(newList) }
                this@flow.emit(it)
            }
    }
}