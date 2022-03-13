package com.picpay.desafio.android.repository.local

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.model.UserLocal

interface UserLocalDataSource {
    fun insert(users: List<User>)
    fun getUser(): LiveData<List<UserLocal>?>
}