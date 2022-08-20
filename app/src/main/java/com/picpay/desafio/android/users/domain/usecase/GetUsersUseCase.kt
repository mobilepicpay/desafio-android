package com.picpay.desafio.android.users.domain.usecase

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.domain.model.UserError
import com.picpay.desafio.android.users.domain.model.User

interface GetUsersUseCase {

    suspend operator fun invoke(): Result<List<User>, UserError>
}