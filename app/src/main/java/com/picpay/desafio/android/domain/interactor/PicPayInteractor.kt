package com.picpay.desafio.android.domain.interactor

interface PicPayInteractor {

    suspend fun getUsers(): PicPayState.GetUsers
}
