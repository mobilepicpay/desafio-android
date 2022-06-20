package com.picpay.desafio.contact_list.presentation.viewmodel

sealed class ContactUiError {
    object UnknownError : ContactUiError()
    object InternetConnection : ContactUiError()
}