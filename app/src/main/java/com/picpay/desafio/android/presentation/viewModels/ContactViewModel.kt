package com.picpay.desafio.android.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.bases.BaseViewModel
import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContactViewModel(private val listContactsUseCase: ListContactsUseCase) : BaseViewModel() {

    private val _contacts: MutableLiveData<List<ContactModel>> = MutableLiveData()
    val contacts: LiveData<List<ContactModel>> get() = _contacts

    fun loadContacts() {
        viewModelScope.launch {
            listContactsUseCase()
                .onStart { startLoading() }
                .catch { setMessageResource(R.string.error) }
                .onCompletion { stopLoading() }
                .collect { _contacts.postValue(it) }
        }
    }
}