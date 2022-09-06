package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val listContactsUseCase: ListContactsUseCase
) : BaseViewModel<ContactModel>() {

    fun loadContacts() {
        viewModelScope.launch {
            listContactsUseCase.execute()
                .onStart {
                    startLoading()
                }
                .catch {
                    with(Exception("Could not connect to PicPay API")) {
                        setError(this)
                    }
                }
                .collect {
                    setSuccess(it)
                }
        }
    }
}