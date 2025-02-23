package com.picpay.desafio.android.presentation.ui.features.contacts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.features.contacts.model.ContactsScreen
import com.picpay.desafio.android.domain.features.contacts.usecase.GetContactsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

const val CONTACTS_STATE = "CONTACTS_STATE"
const val CONTACTS_PARAM = "users"

class ContactsViewModel(
    private val getContactsUseCase: GetContactsUseCase,
    private val savedState: SavedStateHandle,
) : ViewModel(), ContactsListener {
    private val _screen = MutableStateFlow(ContactsScreen())
    val screen: StateFlow<ContactsScreen> = _screen.asStateFlow()

    init {
        if (savedState.contains(CONTACTS_STATE).not()) {
            getUsers()
        } else {
            savedState.get<ContactsScreen>(CONTACTS_STATE)?.let {
                viewModelScope.launch {
                    _screen.emit(it)
                }
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getContactsUseCase(
                CONTACTS_PARAM,
                screen.value
            )
                .onEach {
                    savedState[CONTACTS_STATE] = it
                }
                .collect {
                    _screen.emit(it)
                }
        }
    }

    override fun onButtonClicked() {
        getUsers()
    }
}