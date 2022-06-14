package com.picpay.desafio.android.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContactViewModel(private val listContactsUseCase: ListContactsUseCase) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _messages: MutableLiveData<Int> = MutableLiveData()
    val messages: LiveData<Int> get() = _messages

    private val _contacts: MutableLiveData<List<UserModel>> = MutableLiveData()
    val contacts: LiveData<List<UserModel>> get() = _contacts

    fun loadContacts() {
        viewModelScope.launch {
            listContactsUseCase()
                .onStart { _isLoading.postValue(true) }
                .catch {
                    _isLoading.postValue(false)
                    _messages.postValue(R.string.error)
                }
                .onCompletion { _isLoading.postValue(false) }
                .collect { _contacts.postValue(it) }
        }
    }
}