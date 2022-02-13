package com.picpay.desafio.userlist.domain.usecase

import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.common.base.UseCase
import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.flow.Flow

abstract class GetUserListUseCase: UseCase<Unit, Flow<Resource<List<User>>>>()