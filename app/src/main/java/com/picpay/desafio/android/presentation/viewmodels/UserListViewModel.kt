package com.picpay.desafio.android.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.usecases.GetLocalUsersUseCase
import com.picpay.desafio.android.domain.usecases.GetRemoteUsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
//    private val getUsersLocal: GetLocalUsersUseCase,
    private val getUsersRemote: GetRemoteUsersUseCase,
) : ViewModel() {

    fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getUsersRemote()
            withContext(Dispatchers.Main) {
                when (response) {
                    is Result.Success -> println("weeee ha! ${response.data}")
                    is Result.Error -> println("fail! ${response.exception}")
                }
            }
        }
    }

}