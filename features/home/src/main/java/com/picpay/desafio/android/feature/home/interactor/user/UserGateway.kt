package com.picpay.desafio.android.feature.home.interactor.user

interface UserGateway {

    fun getUserList(): List<UserEntity>
}