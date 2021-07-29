package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.source.local.UserDb
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: PicPayUseCase) : ViewModel() {

    //https://chao2zhang.medium.com/converting-livedata-to-flow-lessons-learned-9362a00611c8
    private val _listDataHomeMain = MutableLiveData<Resource<List<UserDb>>>()
    val users: LiveData<Resource<List<UserDb>>> get() = _listDataHomeMain

    fun start() = viewModelScope.launch {
        useCase.invoke().collect {
            _listDataHomeMain.postValue(it)
        }
    }

}