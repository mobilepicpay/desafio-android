package com.picpay.desafio.userlist.domain.usecase

import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetUserListUseCaseImpl(
    private val repository: UserListRepository
): GetUserListUseCase() {
    override suspend fun execute(params: Unit): Flow<Resource<List<User>>> {
        return repository.getUserList()
            .catch { emit(Resource.error(it)) }
    }
}