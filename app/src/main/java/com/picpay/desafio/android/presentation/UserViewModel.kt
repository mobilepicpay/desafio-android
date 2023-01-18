package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class UserViewModel constructor(private val mainDataUseCase: GetUsersUseCase) : ViewModel() {

    init {
        refresh()
    }

    val uiLiveData: LiveData<UserViewState> =
        mainDataUseCase.resultFlow.flatMapMerge {
            return@flatMapMerge flowOf(
                when (it) {
                    is Outcome.Error -> UserViewState.Error(error = it.error)
                    is Outcome.Loading -> UserViewState.Loading
                    is Outcome.Success -> UserViewState.Success(list = it.data)
                }
            )
        }.asLiveData(Dispatchers.Main)

    fun refresh() {
        viewModelScope.launch {
            mainDataUseCase.launch()
        }
    }
}
