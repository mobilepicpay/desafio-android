package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.repository.model.UserLocal

interface UserRepository {
    fun getUsers(success: () -> Unit, failure: (String) -> Unit)
    fun getUserDao(): LiveData<List<UserLocal>?>
}