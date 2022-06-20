package com.picpay.desafio.contact_list.presentation.viewmodel

sealed class ContactUserEvent {
    object OnInitScreen: ContactUserEvent()
}