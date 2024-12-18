package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.ui.contacts.ContactsScreen
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val activityViewModel by viewModel<ContactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityViewModel.getUsers()

        setContent {
            ContactsScreen()
        }
    }

}