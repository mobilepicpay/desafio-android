package com.picpay.desafio.android.feature.home.gateway

import com.picpay.desafio.android.feature.home.entity.UserEntity

interface UserGateway {

    fun getUserList(): List<UserEntity>
}