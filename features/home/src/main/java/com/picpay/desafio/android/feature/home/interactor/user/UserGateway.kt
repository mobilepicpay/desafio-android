package com.picpay.desafio.android.feature.home.interactor.user

interface UserGateway {

    suspend fun getUserList(): List<UserEntity>
}