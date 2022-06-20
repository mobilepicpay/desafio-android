package com.picpay.desafio.feature.contactlist.usecase

interface GetUsersUseCase {
    suspend operator fun invoke(): Result<List<UserDomain>>
}