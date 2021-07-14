package com.picpay.desafio.android.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.shared.base.BaseViewModel
import com.picpay.desafio.android.shared.extensions.postUnit
import com.picpay.desafio.android.shared.model.ResultWrapper
import com.picpay.desafio.android.shared.model.ViewUser
import kotlinx.coroutines.launch

/**
 * @author Vitor Herrmann on 12/07/2021
 */
class ContactsViewModel(
    private val interactor: ContactsInteractor
) : BaseViewModel() {

    private val mOnFetchContactsSuccess = MutableLiveData<List<ViewUser>>()
    val onFetchContactsSuccess: LiveData<List<ViewUser>> get() = mOnFetchContactsSuccess

    fun fetchContacts() = viewModelScope.launch {
        interactor.getContacts().let {
            if (it.isNullOrEmpty()) updateContacts()
            else fillContacts(it)
        }
    }

    fun updateContacts() = viewModelScope.launch {
        mOnShowLoading.postUnit()
        interactor.fetchContacts().let {
            mOnHideLoading.postUnit()
            when (it) {
                is ResultWrapper.Success -> {
                    interactor.saveContacts(it.value)
                    fillContacts(it.value)
                }
                is ResultWrapper.Error -> mOnError.postValue(it.throwable)
            }
        }
    }

    private fun fillContacts(contacts: List<ViewUser>) {
        mOnFetchContactsSuccess.postValue(contacts)
    }
}
