package com.picpay.desafio.android.contacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.commons.base.BaseViewModel
import com.picpay.desafio.android.commons.base.SchedulerProvider
import com.picpay.desafio.android.contacts.domain.usecase.GetContacts
import com.picpay.desafio.android.contacts.presentation.ContactsViewState
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ContactsViewModel(
    getContacts: GetContacts,
    schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val viewState: LiveData<ContactsViewState> by lazy { _viewState }
    private val _viewState by lazy { MutableLiveData<ContactsViewState>() }
    private val initState by lazy {
        ContactsViewState(
            contacts = emptyList(),
            isLoading = true,
            hasError = false
        )
    }

    init {
        getContacts
            .execute()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { _viewState.value = initState }
            .subscribeBy(
                onSuccess = { _viewState.value = initState.copy(isLoading = false, contacts = it) },
                onError = { _viewState.value = initState.copy(isLoading = false, hasError = true) }
            )
            .addTo(disposables)
    }
}