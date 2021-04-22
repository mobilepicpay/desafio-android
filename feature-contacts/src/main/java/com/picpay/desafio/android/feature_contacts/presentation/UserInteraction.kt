package com.picpay.desafio.android.feature_contacts.presentation

sealed class UserInteraction {
    object OpenedScreen : UserInteraction()
    object RequestedFreshContent : UserInteraction()
}
