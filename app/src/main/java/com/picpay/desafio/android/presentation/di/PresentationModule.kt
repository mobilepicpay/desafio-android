package com.picpay.desafio.android.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.picpay.desafio.android.presentation.ui.features.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {
    fun load() = loadKoinModules(viewModelModule,)

    private val viewModelModule = module {
        viewModel { (savedState: SavedStateHandle) ->
            ContactsViewModel(
                getContactsUseCase = get(),
                savedState = savedState,
            )
        }
    }
}