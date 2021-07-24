package com.picpay.desafio.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(useCase: PicPayUseCase) : ViewModel() {
    val users = useCase.invoke().asLiveData()
}