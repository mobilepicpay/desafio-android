package com.picpay.desafio.android.feature.home.interactor.user

sealed class GetUserListError {
    object NoInternet: GetUserListError()
    object ServerError: GetUserListError()
}