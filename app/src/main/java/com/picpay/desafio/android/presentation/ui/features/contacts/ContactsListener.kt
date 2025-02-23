package com.picpay.desafio.android.presentation.ui.features.contacts

import com.picpay.desafio.android.presentation.ui.utils.FeedbackListener

interface ContactsListener : FeedbackListener

object DummyContactsListener : ContactsListener {
    override fun onButtonClicked() = Unit
}