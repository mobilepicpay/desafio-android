package com.picpay.desafio.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.PicpayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    private val picpayRepository: PicpayRepository
) : ViewModel() {

    val list = MutableStateFlow(listOf<User>())

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            picpayRepository.getUsers().collect { response ->
                response.handleResult(
                    onSuccess = {
                        list.value = it
                    },
                    onError = {
                        print(it.toString())
                    }
                )
            }
        }
    }

}
