package com.picpay.desafio.android.contacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.commons.base.BaseViewModel
import com.picpay.desafio.android.commons.base.SchedulerProvider
import com.picpay.desafio.android.contacts.domain.usecase.GetUsers
import com.picpay.desafio.android.contacts.presentation.ContactsViewState

class ContactsViewModel(
    getUsers: GetUsers,
    schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val viewState: LiveData<ContactsViewState> by lazy { _viewState }
    private val _viewState by lazy { MutableLiveData<ContactsViewState>() }

    init {
        getUsers
            .execute()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { _viewState.value = ContactsViewState.ToggleLoading(isLoading = true) }
            .doAfterTerminate { _viewState.value = ContactsViewState.ToggleLoading(isLoading = false) }
            .subscribe(
                { _viewState.value = ContactsViewState.ShowContacts(it) },
                { _viewState.value = ContactsViewState.ShowError }
            )
            .let(disposables::add)
    }
}