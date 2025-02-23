package com.picpay.desafio.android.domain.features.contacts.model

import androidx.compose.runtime.Immutable
import com.picpay.desafio.android.domain.utils.State
import com.picpay.desafio.android.domain.utils.StateBearer
import java.io.Serializable

data class ContactsScreen(
    override val state: State<Any> = State.Loading,
    val contacts: List<Contact>? = null
) : Serializable, StateBearer

@Immutable
data class Contact(
    val id: Int,
    val name: String,
    val username: String,
    val image: String
) : Serializable