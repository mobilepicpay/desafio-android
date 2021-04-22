package com.picpay.desafio.android.feature_contacts.presentation

import com.picpay.desafio.android.feature_contacts.models.UserPresentation

sealed class UsersScreenState {
    object Idle : UsersScreenState()
    object Loading : UsersScreenState()
    data class Success(val value: List<UserPresentation>) : UsersScreenState()
    data class Failed(val reason: Throwable) : UsersScreenState()
}
