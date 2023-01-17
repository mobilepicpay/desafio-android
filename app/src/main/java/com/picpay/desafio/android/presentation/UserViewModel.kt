package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel constructor(private val mainDataUseCase: GetUsersUseCase) : ViewModel() {

    init {
        refresh()
    }

    val uiLiveData: LiveData<Outcome<List<User>>?> =
        mainDataUseCase.resultFlow.asLiveData(Dispatchers.Main)

    fun refresh() {
        viewModelScope.launch {
            mainDataUseCase.launch()
        }
    }
}
