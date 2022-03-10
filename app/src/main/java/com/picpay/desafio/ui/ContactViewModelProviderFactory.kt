package com.picpay.desafio.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.repository.ContactRepository

class ContactViewModelProviderFactory(val app: Application, val contactRepository: ContactRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactViewModel(app,contactRepository ) as T
    }
}