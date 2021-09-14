package com.picpay.desafio.android.interactor

interface PicPayInteractor {

    suspend fun getUsers(): PicPayState.GetUsers
}
