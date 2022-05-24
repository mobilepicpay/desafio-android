package com.picpay.desafio.android.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ContactState>(ContactState.Initial)
    val state = _state.asStateFlow()

    init {
        loadContacts()
    }

    fun loadContacts() {
        viewModelScope.launch(Dispatchers.Default) {
            _state.emitLoading()
            contactRepository.getContacts()
                .onSuccess {
                    _state.emitSuccess(it)
                }.onFailure {
                    delay(300)
                    _state.emitError()
                }
        }
    }

}