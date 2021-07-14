package com.picpay.desafio.android.contacts

import com.picpay.desafio.android.shared.usecases.FetchContactsUseCase
import com.picpay.desafio.android.shared.usecases.GetContactsUseCase
import com.picpay.desafio.android.shared.usecases.SaveContactsUseCase

/**
 * @author Vitor Herrmann on 13/07/2021
 */
class ContactsInteractor(
    private val fetchContactsUseCase: FetchContactsUseCase,
    private val saveContactsUseCase: SaveContactsUseCase,
    private val getContactsUseCase: GetContactsUseCase
) : FetchContactsUseCase by fetchContactsUseCase,
    SaveContactsUseCase by saveContactsUseCase,
    GetContactsUseCase by getContactsUseCase
