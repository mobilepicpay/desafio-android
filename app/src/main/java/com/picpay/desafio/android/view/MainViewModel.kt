package com.picpay.desafio.android.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.interactor.PicPayInteractor
import com.picpay.desafio.android.domain.interactor.PicPayState
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactor: PicPayInteractor
) : ViewModel() {

    private val mGetUsersState = MutableLiveData<PicPayState.GetUsers>()
    val getUsersState: LiveData<PicPayState.GetUsers> = mGetUsersState

    fun getUsers() {
        viewModelScope.launch {
            mGetUsersState.value = interactor.getUsers()
        }
    }
}
